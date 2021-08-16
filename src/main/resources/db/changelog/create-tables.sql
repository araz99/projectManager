create table pm_user
(
    id                bigserial    not null primary key,
    username          varchar(255) not null,
    password          varchar(255) not null,
    registration_date date         not null
);
create table project
(
    id              bigserial    not null primary key,
    project_name    varchar(255) not null,
    key             varchar(12)  not null,
    lead_id         int8         not null,
    project_type_id int8         not null,
    created_date    date         not null,
    last_modified   timestamp    not null,
    description     text
);

create table task
(
    id            bigserial    not null primary key,
    task_name     varchar(255) not null,
    author_id     int8         not null,
    executor_id   int8,
    issue_type_id int8         not null,
    project_id    int8         not null,
    priority_id   int8,
    status_id     int8         not null,
    created_date  date         not null,
    last_modified timestamp    not null,
    description   text
);
create table role
(
    id   bigserial    not null primary key,
    name varchar(255) not null
);
create table project_type
(
    id                bigserial    not null primary key,
    project_type_name varchar(255) not null
);
create table issue_type
(
    id              bigserial    not null primary key,
    issue_type_name varchar(255) not null
);
create table task_priority
(
    id            bigserial    not null primary key,
    priority_name varchar(255) not null
);
create table task_status
(
    id          bigserial    not null primary key,
    status_name varchar(255) not null
);
create table pm_user_roles
(
    user_id  int8 not null,
    roles_id int8 not null,
    primary key (user_id, roles_id)
);
create table pm_user_projects
(
    users_id    int8 not null,
    projects_id int8 not null,
    primary key (users_id, projects_id)
);
create table pm_user_tasks
(
    user_id  int8 not null,
    tasks_id int8 not null,
    primary key (user_id, tasks_id)
);
alter table pm_user_tasks
    add constraint UK_pm_user_tasks_id unique (tasks_id);
alter table pm_user_projects
    add constraint FK_pm_user_projects_id foreign key (projects_id) references project;
alter table pm_user_projects
    add constraint FK_pm_user_projects_users_id foreign key (users_id) references pm_user;
alter table pm_user_roles
    add constraint FK_pm_user_roles_id foreign key (roles_id) references role;
alter table pm_user_roles
    add constraint FK_pm_user_roles_users_id foreign key (user_id) references pm_user;
alter table pm_user_tasks
    add constraint FK_pm_user_tasks_id foreign key (tasks_id) references task;
alter table pm_user_tasks
    add constraint FK_pm_user_tasks_user_id foreign key (user_id) references pm_user;
alter table project
    add constraint FK_project_lead_id foreign key (lead_id) references pm_user;
alter table project
    add constraint FK_project_project_type_id foreign key (project_type_id) references project_type;
alter table task
    add constraint FK_task_author_id foreign key (author_id) references pm_user;
alter table task
    add constraint FK_task_executor_id foreign key (executor_id) references pm_user;
alter table task
    add constraint FK_task_issue_type_id foreign key (issue_type_id) references issue_type;
alter table task
    add constraint FK_task_priority_id foreign key (priority_id) references task_priority;
alter table task
    add constraint FK_task_project_id foreign key (project_id) references project;
alter table task
    add constraint FK_task_status_id foreign key (status_id) references task_status;