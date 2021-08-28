import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";

const AddProjectTask = (props) => {
  const id = props.match.params.id;

  const [task, setTask] = useState({
    summary: "",
    acceptanceCriteria: "",
    dueDate: null,
    priority: 0,
    status: "",
  });

  const [nullCheck, setNullCheck] = useState(false);

  const changeValue = (e) => {
    setTask({
      ...task,
      [e.target.name]: e.target.value,
    });
    console.log(e.target.value);
  };

  const submitTask = (e) => {
    e.preventDefault();
    const headers = {
      "Content-Type": "application/json;charset=utf-8",
    };
    if (!task.summary) {
      alert("summary 빈칸 안되요");
      setNullCheck(true);
    } else if (task.summary !== null) {
      setNullCheck(false);
    }
    axios
      .post("http://localhost:8080/api/backlog/" + id, task, { headers })
      .then((res) => {
        console.log(res.data);
        setTask(res.data);
        props.history.push("/");
      })
      .catch((err) => {
        console.log("에러", err);
      });
  };

  return (
    <div>
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`/projectBoard/${id}`} className="btn btn-light">
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Add Project Task</h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={submitTask}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="summary"
                    placeholder="Project Task summary"
                    onChange={changeValue}
                  />
                  {nullCheck && <div>빈칸입니다.</div>}
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    onChange={changeValue}
                  ></textarea>
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    onChange={changeValue}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    onChange={changeValue}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    onChange={changeValue}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
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

export default AddProjectTask;
