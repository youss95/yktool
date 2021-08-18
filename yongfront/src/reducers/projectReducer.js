import { GET_PROJECTS } from "../actions/types";

//1. type만들기 2. reducer 만들기 3. index에 설정 4.projectaction

const initialState = {
  projects: [],
  project: {},
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload,
      };

    default:
      return state;
  }
};

export default reducer;
