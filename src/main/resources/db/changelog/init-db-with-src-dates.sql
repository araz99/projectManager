insert into role (id, name)
values (1, 'USER'),
       (2, 'ADMIN');

insert into issue_type (id, issue_type_name)
values (1, 'improvement'),
       (2, 'task'),
       (3, 'new future'),
       (4, 'bug');
insert into project_type (id, project_type_name)
values (1, 'Software'),
       (2, 'Business'),
       (3, 'Health Care');
insert into task_priority (id, priority_name)
values (1, 'low'),
       (2, 'medium'),
       (3, 'high');
insert into task_status (id, status_name)
values (1, 'to do'),
       (2, 'in progress'),
       (3, 'in review'),
       (4, 'done');