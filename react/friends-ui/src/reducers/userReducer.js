const initialState = {
  users: [
    {
      id: 1,
      name: "Alice",
      city: "Dublin",
      friends: [3]
    },
    {
      id: 2,
      name: "Bob",
      city: "Dublin",
      friends: [3, 4, 5]
    },
    {
      id: 3,
      name: "Charlie",
      city: "London",
      friends: [1, 2, 5]
    },
    {
      id: 4,
      name: "Davina",
      city: "Belfast",
      friends: [2, 5]
    },
    {
      id: 5,
      name: "John",
      city: "Galway",
      friends: [2, 3, 4]
    }
  ]
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case "ADD_USER":
      return state;
    case "GET_USERS":
      return state;
    case "ADD_FRIEND":
      return state;
    case "SORT_FRIENDS":
      return state;
    default:
      return state;
  }
};

export default userReducer;
