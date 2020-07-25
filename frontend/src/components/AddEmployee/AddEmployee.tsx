import React, { useState } from 'react';
import { Container, CssBaseline, makeStyles, Theme, Avatar, Typography, Grid, TextField, Button } from '@material-ui/core';
import GroupIcon from '@material-ui/icons/Group';

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

    },
    submit:{

    },
    textField: {

    }
}));

const AddEmployee = () => {
    const classes = useStyles();
    const [firstLoad, setLoad] = useState<boolean>(true);
    const [name, setName] = useState<string>("");
    const [department, setDepartment] = useState<string>("");
    const [gender, setGender] = useState<string>("");
    const [selectedDate, setSelectedDate] = useState<Date>(new Date());

    const handleNameChange = (e: any) => setName(e.target.value);
    const handleDepartmentChange = (e: any) => setDepartment(e.target.value);
    const handleGenderChange = (e: any) => setGender(e.target.value);
    const handleDateChange = (date: any) => setSelectedDate(date);
    
    const handleSubmit = () => {
        
    }

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
                        <Button
                         fullWidth
                         variant="contained"
                         color="primary"
                         className={classes.submit}
                         onClick={handleSubmit}
                        >Save</Button>
                    </Grid>
                </form>
            </div>
        </Container>
    )
}

export default AddEmployee;