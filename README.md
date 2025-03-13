# To Dos Application Backend

## Assumptions

1. Each task on a to-dos application would fall under one category 1-1 relationship
2. Each category will have a collection of tasks - 1-m relationship

### Category Entity

- id
- name
- description

### Task Entity

- id
- name
- description
- category
- status In progress | Done | Archived | Deleted
- createdDate
- updatedDate

## Considerations

- fetching a large amount of data

### HOW TO START PROJECT

- Set your .env file please see the .env-example
- run these commands in the bash to use the path variables in the application.properties
  1. export DB_NAME={your_db_name}
  2. export DB_PORT={your_db_port}
  3. export DB_USER={your_db_username}
  4. export DB_PASS={your_db_password}
