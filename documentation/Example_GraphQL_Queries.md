
# Example Queries for our GraphQL implementation

---

- [Series](#series)
  - [Create Series](#create-series)
  - [Get All Series (only names)](#get-all-series-only-names)
  - [Get All Series](#get-all-series)
  - [Get Series by ID](#get-series-by-id)
  - [Update Series](#update-series)
  - [Delete Series](#delete-series)


- [Expansion Sets](#expansion-sets)
  - [Add Expansion Set to Series](#add-expansion-set-to-series)
  - [Get All Expansion Sets](#get-all-expansion-sets)
  - [Get All Expansion Set (for specified Series)](#get-all-expansion-sets-for-specified-series)
  - [Update Expansion Set](#update-expansion-set)
  - [Delete Expansion Set](#delete-expansion-set)
  

- [Cards](#tech-stack)
  - [Add Card to Expansion Set](#add-card-to-expansion-set)
  - [Update Card](#update-card)
  - [Delete Card](#delete-card)

---

## Series

### Create Series
```graphql
mutation createSeries {
  createSeries(
    input: { name: "Destined Rivals", icon: "https://pokemonsetimages.pokedata.io/Sets/Series_Images/Sword%20&%20Shield.webp"}) {
      id
      name
      icon
  }
}
```

### Get All Series (Only names)
```graphql
query getAllSeriesOnlyName {
  findAllSeries {
    id
    name
  }
}
```

### Get All Series
```graphql
query getListOfSeries {
  findAllSeries {
    id
    name
    icon
    expansionSets{
      id
      name
      cards{
        id
        face
        name
        number
        collected
        price
      }
    }
  }
}
```

### Get Series by ID
```graphql
query getSeriesById {
  findSeriesById(id: "694083a74c4e48d8c5635073") {
    id
    name
    icon
    expansionSets{
      id
      name
      cards{
        id
        face
        name
        number
        collected
        price
      }
    }
  }
}
```

### Update Series
```graphql
mutation updateSeries {
    updateSeries(
        id: "69408374e996ef5755255be8",
        name: "Mega Evolution",
        icon: "https://pokemonsetimages.pokedata.io/Sets/Series_Images/Mega%20Evolution.webp") {
        id
        name
        icon
    }
}
```

### Delete Series
```graphql
mutation deleteSeries {
    deleteSeries(id: "694f04351419ab8a848089b2")
}
```

## Expansion Sets

### Add Expansion Set to Series
```graphql
mutation addExpansionSetToSeries {
    addExpansionSetToSeries(
        seriesId: "694083a74c4e48d8c5635073",
        input: {
            name: "Journey Together",
            icon: "https://den-media.pokellector.com/logos/Phantasmal-Flames.logo.424.png"}) {
        id
        name
        icon
    }
}
```

### Get all Expansion Sets
```graphql
query getAllExpansionSets {
    findAllSeries {
        id
        name
        expansionSets{
            id
            name
            icon
        }
    }
}
```

### Get All Expansion Sets (for specified Series)
```graphql
query getAllExpansionSetsForSeries {
    findSeriesById (id: "694083a74c4e48d8c5635073") {
        expansionSets{
            id
            name
            icon
        }
    }
}
```

### Update Expansion Set
```graphql
mutation updateExpansionSet {
    updateExpansionSet(
        seriesId: "694083af4c4e48d8c5635074",
        expansionSetId: "0df243ed-f568-4888-9a1a-afdf4a7909dc",
        name: "Astral Radiance Trainer Gallery"
        icon: "https://den-media.pokellector.com/logos/Astral-Radiance-Trainer-Gallery.logo.348.png"
    ) {
        id
        name
        icon
    }
}
```

### Delete Expansion Set
```graphql
mutation deleteExpansionSet {
    deleteExpansionSet(seriesId: "694083a74c4e48d8c5635073", expansionSetId: "d65eb745-22b1-4aad-964d-f0bc9945163f")
}
```

## Cards

### Add Card to Expansion Set
```graphql
mutation addCardToExpansionSet {
    addCardToExpansionSet(
        seriesId: "694083a74c4e48d8c5635073",
        expansionSetId: "dc99820d-5752-4411-b597-10d9a376d73a",
        input:  {
            name: "Team Rocket's Mewtwo ex"
            face: "https://pokemoncardimages.pokedata.io/images/Destined+Rivals/231.webp"
            number: "151"
            collected: false
            price: 55.3
        }) {
        id
        face
        name
        number
        price
    }
}
```

### Update Card
```graphql
mutation updateCard {
    updateCard(
        seriesId: "693f3026787a96a53d0a497e",
        expansionSetId: "89edb1f3-bdab-4bec-aeec-56627a43fe40",
        cardId: "8ba26cc7-c4a8-4fac-abf9-4badae5e78af",
        name: "Pipi"
        number: "15"
        collected: false
        price: 55.3
    ) {
        id
        name
        number
        collected
        price
    }
}
```

### Delete Card
```graphql
mutation deleteCard {
    deleteCard(
        seriesId: "694083a74c4e48d8c5635073",
        expansionSetId: "dc99820d-5752-4411-b597-10d9a376d73a",
        cardId: "ec076bde-6f9b-4ea0-983e-ec23276d1bf6"
    )
}
```