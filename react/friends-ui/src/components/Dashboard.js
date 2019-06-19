import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button"

/**
 * Dashboard component to show admin data and functionality
 * @param {object} props Component props
 * @param {string} props.className CSS className for this component
 */
const Dashboard = ({ className, users }) => {

  function getFriendButtons(user) {
    let friends = [];
    user.friends.forEach(friend => {
      friends.push(<a href="#">{friend} </a>);
    })
    return friends;
  }
  let rows = [];
  console.log("users:\n", JSON.stringify(users, null, 2));
  for (var i = 0; i < users.length; i++) {
    // note: we add a key prop here to allow react to uniquely identify each
    // element in this array. see: https://reactjs.org/docs/lists-and-keys.html
    rows.push(
      <tr key={users[i].id}>
        <td>{users[i].id}</td>
        <td>{users[i].name}</td>
        <td>{users[i].city}</td>
        <td>{getFriendButtons(users[i])}</td>
        <td>{users[i].friends}</td>
      </tr>
    );
  }
  console.log("rows:\n", rows);

  return (
    <div className={className}>
      <Table responsive>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>City</th>
            <th>Friends</th>
            <th>Suggested Friends</th>
          </tr>
        </thead>
        <tbody>{rows}</tbody>
      </Table>
    </div>
  );
};

Dashboard.propTypes = {
  className: PropTypes.string.isRequired
  // app: PropTypes.shape({
  //   appId: PropTypes.string
  // }).isRequired
};

const mapStateToProps = state => {
  console.log("state:\n", state);
  return {
    users: state.users
  };
};

const mapDispatchToProps = {};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Dashboard);
