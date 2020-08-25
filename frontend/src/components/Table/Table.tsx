import React, { useState } from 'react';
import { makeStyles, Theme, Avatar, Typography, CircularProgress, TableContainer, Paper } from '@material-ui/core';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import GroupIcon from '@material-ui/icons/Group';
import { ToInput } from '../AddEmployee/AddEmployee';
import { Link } from 'react-router-dom';

const useStyles = makeStyles((theme: Theme) => ({
    table: {
        minWidth: 600
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main
    },
    paper: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        margin: `10px`,
        height: "100%",
        width: "99%",
        marginTop: theme.spacing(7)
    },
    link: {
        color: "rgba(0,0,0,0.65)",
        textDecoration: "none",
        marginLeft: "10%",
        alignSelf: "flex-start",
        "&:hover": {
            color: "rgba(0,0,0,1)"
        }
    }
}));

type FromDB = ToInput & {
    id: number;
}

const SimpleTable = () => {
    const classes = useStyles();

    const [employees, setEmployees] = useState<Array<FromDB> | undefined>([]);
    const [loading, setLoading] = useState<boolean>(true);

    async function getEmployees() {
        let response = await fetch("/api/employee");
        let body = await response.json() as Array<FromDB>;
        console.log(body);
        const sortedData = body.sort((a, b) => a.id - b.id)
        setEmployees(sortedData);
    }

    if (loading) {
        getEmployees();
        setLoading(false);
    }

    return (
        <div className={classes.paper}>
            <Avatar className={classes.avatar}>
                <GroupIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                Employee Directory
            </Typography>
            {loading ?
                (
                    <CircularProgress />
                )
                : <TableContainer
                    style={{ width: "80%", margin: "0 10px" }}
                    component={Paper}
                >
                    <Table className={classes.table} aria-label="table">
                        <TableHead>
                            <TableRow>
                                <TableCell style={{ textAlign: "center", fontWeight: 800 }}>Id</TableCell>
                                <TableCell style={{ textAlign: "center", fontWeight: 800 }}>Name</TableCell>
                                <TableCell style={{ textAlign: "center", fontWeight: 800 }}>Department</TableCell>
                                <TableCell style={{ textAlign: "center", fontWeight: 800 }}>Gender</TableCell>
                                <TableCell style={{ textAlign: "center", fontWeight: 800 }}>DOB</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {employees ? (employees.map((emp: FromDB) => (
                                <TableRow key={emp.name}>
                                    <TableCell align="center">{emp.id}</TableCell>
                                    <TableCell align="center">{emp.name}</TableCell>
                                    <TableCell align="center">{emp.department}</TableCell>
                                    <TableCell align="center">{emp.gender}</TableCell>
                                    <TableCell align="center">{emp.dob}</TableCell>
                                </TableRow>
                            ))) : <div>data is missing for body</div>}
                        </TableBody>
                    </Table>
                </TableContainer>}
            <Link className={classes.link} to="/">
                <Typography align="left">
                    &#x2190; Head back to save data
                </Typography>
            </Link>
        </div>
    )
}

export default SimpleTable;