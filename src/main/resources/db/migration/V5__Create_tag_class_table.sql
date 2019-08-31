create TABLE tag_class(
    id int AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(20) NOT NULL
);

create TABLE tag(
    id int AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(20) NOT NULL,
    class_id int not null
);

INSERT tag_class(name)VALUES ('编程语言');
INSERT tag(name, class_id)
                  VALUES ('java', 1),
                         ('c', 1),
                         ('c++', 1);

select tc.name, t.name
from tag t
INNER JOIN tag_class tc
on t.class_id = tc.id;