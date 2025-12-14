
## Example Queries for our GraphQL implementation

Find one
```text
query getSingleCard {
  findCardById(id: "1") {
    id
    name
    number
    collected
    price
  }
}
```

Find all
```text
query getListOfCards {
  findAllCards {
    id
    name
    number
    collected
    price
  }
}
```

Create
```text
mutation createCard {
  createCard(input: { name: "Snorlax", number: 33, collected: true, price: 54.75 }) {
    id
    name
    number
    collected
    price
  }
}
```

Update
```text
mutation updateCard {
  updateCard(id: "69381e8d171471bdd6f6d53d", name: "Charmander", number: 1) {
    id
    name
    number
    collected
    price
  }
}
```

Delete
```text
mutation deleteCard {
  deleteCard(id: "69381e8d171471bdd6f6d53d") {
    id
    name
    number
    collected
    price
  }
}
```