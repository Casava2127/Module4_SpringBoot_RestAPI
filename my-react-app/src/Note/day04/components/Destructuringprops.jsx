import React from 'react'
// Import React library

import ReactDOM from 'react-dom'
// Import ReactDOM for rendering components into the DOM

import asabenehImage from './../images/asabeneh.jpg'
// Import an image from the local directory

// Function to show formatted month, date, and year
const showDate = (time) => {
    const months = [
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

    const month = months[time.getMonth()].slice(0, 3) // Get the month as a three-letter abbreviation
    const year = time.getFullYear() // Get the full year
    const date = time.getDate() // Get the date (day of the month)
    return ` ${month} ${date}, ${year}` // Return formatted date string
}

// Header Component
const Header = ({
                    data: { // Destructure props and extract the `data` object
                        welcome,
                        title,
                        subtitle,
                        author: { firstName, lastName },
                        date,
                    },
                }) => {
    return ( // Return JSX
        <header>
            <div className='header-wrapper'>
                {/* Apply className for styling */}
                <h1>{welcome}</h1>
                <h2>{title}</h2>
                <h3>{subtitle}</h3>
                <p>
                    {firstName} {lastName}
                </p>
                {/* Pass date as an argument to the showDate function */}
                <small>{showDate(date)}</small>
            </div>
        </header>
    )
}

// TechList Component
const TechList = ({ techs }) => { // Receive `techs` as a prop
    const techList = techs.map((tech) => <li key={tech}>{tech}</li>) // Use the map function to generate a list of techs
    return techList // Return the JSX list
}

// User Card Component
const UserCard = ({ user: { firstName, lastName, image } }) => ( // Destructure the user prop
    <div className='user-card'>
        <img src={image} alt={firstName} />
        <h2>
            {firstName} {lastName}
        </h2>
    </div>
)

// A Button Component
const Button = ({ text, onClick, style }) => ( // Receive text, onClick, and style as props
    <button style={style} onClick={onClick}>
        {text}
    </button>
)

// CSS styles in JavaScript Object
const buttonStyles = {
    backgroundColor: '#61dbfb',
    padding: 10,
    border: 'none',
    borderRadius: 5,
    margin: 3,
    cursor: 'pointer',
    fontSize: 18,
    color: 'white',
}

// Main Component
const Main = ({ user, techs, greetPeople, handleTime }) => ( // Receive multiple props in the Main component
    <main>
        <div className='main-wrapper'>
            <p>Prerequisites to get started with React.js:</p>
            <ul>
                <TechList techs={techs} />
                {/* Pass the techs prop to the TechList component */}
            </ul>
            <UserCard user={user} />
            {/* Pass the user prop to the UserCard component */}
            <Button text='Greet People' onClick={greetPeople} style={buttonStyles} />
            <Button text='Show Time' onClick={handleTime} style={buttonStyles} />
        </div>
    </main>
)

// Footer Component
const Footer = ({ copyRight }) => ( // Receive the copyRight prop
    <footer>
        <div className='footer-wrapper'>
            <p>Copyright {copyRight.getFullYear()}</p>
        </div>
    </footer>
)

// The App, or the parent container component
const Destructuringprops = () => {
    // Define data for the Header and Main components
    const data = {
        welcome: 'Welcome to 30 Days Of React',
        title: 'Getting Started with React',
        subtitle: 'JavaScript Library',
        author: {
            firstName: 'Asabeneh',
            lastName: 'Yetayeh',
        },
        date: new Date(), // Store the current date
    }
    const date = new Date() // Create a new Date instance

    const techs = ['HTML', 'CSS', 'JavaScript'] // Define an array of technologies

    // Copy author data into the user object using the spread operator and add an image property
    const user = { ...data.author, image: asabenehImage }

    const handleTime = () => {
        alert(showDate(new Date()))
    }
    const greetPeople = () => {
        alert('Welcome to 30 Days Of React Challenge, 2020')
    }

    // Return JSX
    return (
        <div className='app'>
            <Header data={data} />
            {/* Pass the data object as a prop to the Header component */}
            <Main
                user={user}
                techs={techs}
                handleTime={handleTime}
                greetPeople={greetPeople}
            />
            {/* Pass user, techs, handleTime, and greetPeople as props to the Main component */}
            <Footer copyRight={date} />
            {/* Pass the date object as the copyRight prop to the Footer component */}
        </div>
    )
}

export default Destructuringprops // Export the Destructuringprops component
