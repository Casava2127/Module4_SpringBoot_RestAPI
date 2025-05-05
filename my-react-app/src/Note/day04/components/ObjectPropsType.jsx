import React from 'react' // import REact library
// import Header from "../../day04/components/Header.jsx";
// The App, or the parent or the container component
// Functional Component

// Header Components
// Function to show formatted month, date, and year
const showDate = (time) => {
    const months = [ // declare array of months
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December',
    ]

    const month = months[time.getMonth()].slice(0, 3) // get the month as a three-letter
    const year = time.getFullYear() // get the full  year
    const date = time.getDate() // get the date ( day of month)
    return ` ${month} ${date}, ${year}` // return formatted date String
}
// declare Header component
const Header = (props) => {
    return ( // return JSX
        <header>
            <div className='header-wrapper'>
                {/*Apply className for styling*/}
                <h1>{props.data.welcome}</h1>
                <h2>{props.data.title}</h2>
                <h3>{props.data.subtitle}</h3>
                <p>
                    {props.data.author.firstName} {props.data.author.lastName}
                </p>
                {/*Pass date as an argument to showDate function*/}
                <small>{showDate(props.data.date)}</small>
            </div>
        </header>
    )
}
const ObjectPropsType = () => {
    //define date for the Header and Main components
    const data = {
        welcome: 'Welcome to 30 Days Of React',
        title: 'Getting Started React',
        subtitle: 'JavaScript Library',
        author: {
            firstName: 'Asabeneh',
            lastName: 'Yetayeh',
        },
        date: new Date(), // Store the current date
    }

    return (
        <div className='app'>
            <Header data={data} />
        {/*    pass the data object as a prop to the Header component*/}
        </div>
    )
}
export default ObjectPropsType