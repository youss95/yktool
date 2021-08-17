import { GET_ERRORS } from "../actions/types";

const initialState = {};

//액션의 결과를 걸러내는
const reducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;

    default:
      return state;
  }
};

export default reducer;
