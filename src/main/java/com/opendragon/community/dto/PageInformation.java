package com.opendragon.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/7/27
 */
@Data
public class PageInformation {
    private List<QuestionDTO> questionDTOs;
    private boolean previous;
    private boolean next;
    private boolean first;
    private boolean end;
    private long page;
    private long totalPage;
    private ArrayList<Long> pages = new ArrayList<>();

    public PageInformation() {
    }

    public PageInformation(long page, long pageSize, long questionCount) {
        _init(page, pageSize, questionCount);
    }

    private void _init(long page, long pageSize, long questionCount){
        this.page = page;
        // 计算总的页数
        totalPage = (questionCount%pageSize > 0 ? questionCount/pageSize+1 : questionCount/pageSize);


        pages.add(page);
        for(int i = 1; i <=3; i++){
            if(page-i > 0){
                pages.add(0, page-i);
            }
            if(page+i <= totalPage){
                pages.add(page+i);
            }
        }

        if(page == 1){
            previous = false;
        }else{
            previous = true;
        }
        if(page == totalPage){
            next = false;
        }else{
            next = true;
        }

        if(pages.contains(1L)){
            first = false;
        }else {
            first = true;
        }

        if(pages.contains(totalPage)){
            end = false;
        }else {
            end = true;
        }
    }
}
