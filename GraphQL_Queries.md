
## Example Queries for our GraphQL implementation

Find one
```text
query getSingleCard {
  cardById(id: "2") {
    id
    name
    number
  }
}
```

Find all
```text
query getListOfCards {
  findAll {
    id
    name
    number
  }
}
```

Create
```text
mutation createCard {
  create(name: "Harry", number: 5) {
    id
    name
    number
  }
}
```

Update
```text
mutation updateCard {
  update(id: "2", name: "Frits", number: 999) {
    id
    name
    number
  }
}
```

Delete
```text
mutation deleteCard {
  delete(id: 1) {
    id
    name
    number
  }
}
```