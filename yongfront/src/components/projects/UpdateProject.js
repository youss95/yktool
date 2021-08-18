import axios from "axios";
import React, { useEffect, useState } from "react";

const UpdateProject = (props) => {
  const id = props.match.params.id; //projectIdentifier

  const [dashboard, setDashboard] = useState({
    projectName: "",
    projectIdentifier: "",
    description: "",
    start_date: "",
    end_date: "",
  });
  console.log("ㄴㄴ");
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/project/" + id)
      .then((res) => {
        console.log(res.data);
        setDashboard(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const changeValue = (e) => {
    setDashboard({
      ...dashboard,
      [e.target.name]: e.target.value,
    });
    console.log(e.target.value);
  };

  const submitBoard = (e) => {
    e.preventDefault();
    const headers = {
      "Content-Type": "application/json;charset=utf-8",
    };

    axios
      .put("http://localhost:8080/api/project/" + id, dashboard, { headers })
      .then((res) => {
        console.log(res.data);
        setDashboard(res.data);
        props.history.push("/");
      })

      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">수정</h5>
              <hr />
              <form onSubmit={submitBoard}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="Project Name"
                    name="projectName"
                    onChange={changeValue}
                    value={dashboard.projectName || ""}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    name="projectIdentifier"
                    onChange={changeValue}
                    value={dashboard.projectIdentifier || ""}
                    disabled
                  />
                </div>

                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Project Description"
                    name="description"
                    onChange={changeValue}
                    value={dashboard.description || ""}
                  ></textarea>
                </div>
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="start_date"
                    onChange={changeValue}
                    value={dashboard.start_date || ""}
                  />
                </div>
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="end_date"
                    onChange={changeValue}
                    value={dashboard.end_date || ""}
                  />
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProject;
