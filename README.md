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
