CREATE TABLE employee (
    e_id       NUMBER(10) PRIMARY KEY,
    img_id     NUMBER(10),
    name       VARCHAR2(200),
    username   VARCHAR2(200) UNIQUE,
    password   VARCHAR2(200),
    age        NUMBER(3),
    access     NUMBER(1)
);

CREATE TABLE requests (
    r_id             NUMBER(10) PRIMARY KEY,
    m_id             NUMBER(10),
    e_id             NUMBER(10),
    date_requested   DATE,
    date_completed   DATE,
    amount           DECIMAL(10,2),
    status           NUMBER(1), --0-pending, 1-approved, 2-rejected
    reason           VARCHAR2(200)
);

create sequence make_eid start with 0 increment by 2;
create sequence make_rid start with 1 increment by 2;

create or replace procedure add_employee(img_id in number, name in varchar2, username in varchar2, password in varchar2, age in number, access in number, ret out number)
is begin
insert into employee(make_eid.nextval, img_id, name, username, password, age, access);
ret := 1;
end;

create or replace procedure create_request(m_id in number, e_id in number, date_req in date, amount in decimal, reason in varchar2, ret out number)
is begin
insert into requests(make_rid.nextval, m_id, e_id, date_req, null, amount, 0, reason);
ret:= 1;
end;