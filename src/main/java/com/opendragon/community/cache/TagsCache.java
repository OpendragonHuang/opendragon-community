package com.opendragon.community.cache;

import com.opendragon.community.dto.TagDTO;
import com.opendragon.community.mapper.TagClassMapper;
import com.opendragon.community.mapper.TagExtMapper;
import com.opendragon.community.mapper.TagMapper;
import com.opendragon.community.model.Tag;
import com.opendragon.community.model.TagClass;
import com.opendragon.community.model.TagExample;
import com.opendragon.community.util.SpringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/31
 */
public class TagsCache implements Serializable {
    private static final long serialVersionUID = 1L;
    private static HashMap<String, List<TagDTO>> cache;
    private static TagsCache instance;
    private static TagClassMapper tagClassMapper;
    private static TagMapper tagMapper;

    static {
        cache = new HashMap<>();
        tagClassMapper = SpringUtils.getBean(TagClassMapper.class);
        tagMapper = SpringUtils.getBean(TagMapper.class);
        updateCache();
    }

    public static void updateCache(){
        List<TagClass> tagClassList = tagClassMapper.selectByExample(null);
        for (TagClass tagClass : tagClassList) {

            TagExample tagExample = new TagExample();
            tagExample.createCriteria().andClassIdEqualTo(tagClass.getId());

            List<Tag> tagList = tagMapper.selectByExample(tagExample);

            List<TagDTO> tagDTOList = tagList.stream().map(t -> {
                TagDTO tagDTO = new TagDTO();
                BeanUtils.copyProperties(t, tagDTO);
                return tagDTO;
            }).collect(Collectors.toList());
            cache.put(tagClass.getName(), tagDTOList);
        }
    }

    public static HashMap<String, List<TagDTO>> getCache(){
        return cache;
    }
}
