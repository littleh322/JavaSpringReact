import React, { useState } from 'react';
import { Container, CssBaseline, makeStyles, Theme, Avatar, Typography, Grid, TextField, Button } from '@material-ui/core';
import GroupIcon from '@material-ui/icons/Group';
import { Link } from 'react-router-dom';
import moment from 'moment';

const useStyles = makeStyles((theme: Theme) => ({
    paper: {
      marginTop: theme.spacing(7),
      display: "flex",
      flexDirection: "column",
      alignItems: "center"
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main
    },
    form: {
        width: "100%", // Fix IE 11 issue.
        marginTop: theme.spacing(3)
      },
      submit: {
        margin: theme.spacing(3, 0, 2)
      },
      textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: "100%"
      }
}));

export type ToInput = {
    name:string;
    department: string;
    gender: string;
    dob: string;
}

const AddEmployee = () => {
    const classes = useStyles();
    const [firstLoad, setLoad] = useState<boolean>(true);
    const [name, setName] = useState<string>("");
    const [department, setDepartment] = useState<string>("");
    const [gender, setGender] = useState<string>("");
    const [selectedDate, setSelectedDate] = useState<Date>(new Date());
    const [message, setMessage] = useState<string>("Nothing saved in the session");

    const handleNameChange = (e: any) => setName(e.target.value);
    const handleDepartmentChange = (e: any) => setDepartment(e.target.value);
    const handleGenderChange = (e: any) => setGender(e.target.value);
    const handleDateChange = (date: any) => setSelectedDate(date);
    
    async function sampleFunc(toInput: any) {
        const response = await fetch("/api/employee", {
          method: "POST", 
          mode: "cors",
          cache: "no-cache", 
          credentials: "same-origin", 
          headers: {
            "Content-Type": "application/json"
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          redirect: "follow", 
          referrerPolicy: "no-referrer", 
          body: JSON.stringify(toInput)
        });
        let body = await response.json();
        console.log(body.id);
        setMessage(body.id ? "Data sucessfully updated" : "Data updation failed");
      }

    const handleSubmit = (variables :any) => {
        console.log(moment(selectedDate).local().format('YYYY-MM-DD'));
        const toInput = {name, department, gender, dob: moment(selectedDate).local().format('YYYY-MM-DD')};
        sampleFunc(toInput);
        setName("");
        setDepartment("");
        setGender("");
    }

    if(firstLoad)
        setLoad(false);

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <GroupIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Employee Directory
                </Typography>
                <form className={classes.form}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField 
                                variant="outlined"
                                required
                                fullWidth
                                id="name"
                                value={name}
                                label="Name"
                                name="name"
                                autoComplete="name"
                                onChange={handleNameChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField 
                                variant="outlined"
                                required
                                fullWidth
                                id="department"
                                value={department}
                                label="Department"
                                name="department"
                                autoComplete="department"
                                onChange={handleDepartmentChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField 
                                variant="outlined"
                                required
                                fullWidth
                                id="gender"
                                value={gender}
                                label="Gender"
                                name="gender"
                                autoComplete="gender"
                                onChange={handleGenderChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                required
                                id="date"
                                label="Date of Birth"
                                type="date"
                                className={classes.textField}
                                InputLabelProps={{
                                  shrink: true
                                }}
                                onChange={handleDateChange}
                            />
                        </Grid>
                    </Grid>
                    <Button
                         fullWidth
                         variant="contained"
                         color="primary"
                         className={classes.submit}
                         onClick={handleSubmit}
                        >Save
                    </Button>
                    <Grid container justifyContent="center">
                        <Grid item>
                            <Link to="/view">View Employee Records</Link>
                        </Grid>
                    </Grid>
                </form>
                <Typography style={{margin: 7}} variant="body1">
                    Status: {message}
                </Typography>
            </div>
        </Container>
    )
}

export default AddEmployee;