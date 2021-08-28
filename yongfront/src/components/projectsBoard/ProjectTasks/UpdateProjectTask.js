import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const UpdateProjectTask = (props) => {
  const backlog_id = props.match.params.backlog_id;
  const pt_id = props.match.params.pt_id;

  const [task, setTask] = useState({
    id: "",
    projectSequence: "",
    summary: "",
    acceptanceCriteria: "",
    dueDate: "",
    priority: 0,
    status: "",
    projectIdentifier: "",
    create_At: null,
  });

  const changeValue = (e) => {
    setTask({
      ...task,
      [e.target.name]: e.target.value,
    });
    console.log(e.target.value);
  };

  useEffect(() => {
    axios
      .get(`/api/backlog/${backlog_id}/${pt_id}`)
      .then((res) => {
        console.log(res.data);
        setTask(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const submitTask = (e) => {
    e.preventDefault();
    const headers = {
      "Content-Type": "application/json;charset=utf-8",
    };
    axios
      .patch(`/api/backlog/${backlog_id}/${pt_id}`, task, {
        headers,
      })
      .then((res) => {
        console.log(res.data);
        setTask(res.data);
        props.history.push("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`${backlog_id}/${pt_id}`} className="btn btn-light">
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Update Project Task</h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={submitTask}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="summary"
                    onChange={changeValue}
                    value={task.summary}
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    onChange={changeValue}
                    value={task.acceptanceCriteria}
                  />
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    onChange={changeValue}
                    value={task.dueDate}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    onChange={changeValue}
                    value={task.priority}
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
                    value={task.status}
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

export default UpdateProjectTask;
