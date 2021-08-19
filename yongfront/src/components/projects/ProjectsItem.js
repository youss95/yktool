import axios from "axios";
import { withRouter, Link } from "react-router-dom";

const ProjectsItem = (props) => {
  const { projectName, projectIdentifier, description } = props.dashboard;

  const deleteDashboard = () => {
    if (window.confirm("정말로 삭제하시겠습니까?")) {
      axios
        .delete(`/api/project/${projectIdentifier}`)
        .then((res) => {
          console.log(res.data);
          if (res.data === "ok") {
            window.location.replace("/dashboard");
          } else {
            alert("ff");
          }
        })
        .catch((err) => {
          console.log(err);
          alert("에러");
        });
    }
  };

  return (
    <div>
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{projectIdentifier}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{projectName}</h3>
              <p>{description}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                <a href="#">
                  <li className="list-group-item board">
                    <i className="fa fa-flag-checkered pr-1"> Project Board </i>
                  </li>
                </a>
                <Link to={"/updateProject/" + projectIdentifier}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1"> Update Project Info</i>
                  </li>
                </Link>

                <li
                  className="list-group-item delete"
                  onClick={deleteDashboard}
                >
                  <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default withRouter(ProjectsItem);
