import React from 'react';

const welcome = 'Welcome to 30 Days Of React';
const title = 'Getting Started React';
const subtitle = 'JavaScript Library';
const author = {
    firstName: 'Asabeneh',
    lastName: 'Yetayeh',
};
const date = 'Oct 2, 2020';

function Header() {
    // Khởi tạo giá trị cho numOne và numTwo
    let numOne = 10;
    let numTwo = 20;

    // Tính tuổi
    const yearBorn = 1820;
    const currentYear = new Date().getFullYear();
    const age = currentYear - yearBorn;

    return (
        <>
            <header>
                <div className="header-wrapper">
                    <h1>{welcome}</h1>
                    <h2>{title}</h2>
                    <h3>{subtitle}</h3>
                    <p>
                        Instructor: {author.firstName} {author.lastName}
                    </p>
                    <small>Date: {date}</small>
                </div>
            </header>

            {/* Hiển thị phép toán */}
            <p>
                {numOne} + {numTwo} = {numOne + numTwo}
            </p>

            {/* Hiển thị tuổi */}
            <p>
                {author.firstName} {author.lastName} is {age} years old
            </p>
        </>
    );
}

export default Header;
