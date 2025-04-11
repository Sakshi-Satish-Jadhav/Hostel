document.addEventListener('register', function () {
    const registerForm = document.getElementById('register');
    const allocationList = document.getElementById('allocationList');

    registerForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('email').value.trim();
        const mobileNumber = document.getElementById('mobileNumber').value.trim();
        const course = document.getElementById('course').value.trim();
        const roomNumber = document.getElementById('roomNumber').value.trim();
        const feesPaid = document.getElementById('feesPaid').value.trim();

        if (firstName && lastName && email && mobileNumber && course && roomNumber && feesPaid) {
            const studentID = Math.floor(Math.random() * 10000);
            const fullName = `${firstName} ${lastName}`;

            const newRow = document.createElement('tr');
            newRow.innerHTML = `<td>${studentID}</td>
                <td class="student-name">${fullName}</td>
                <td class="room-number">${roomNumber}</td>
                <td>
                    <button class="delete-btn">Delete</button>
                    <button class="update-btn">Update</button>
                </td>`;

            allocationList.appendChild(newRow);
            alert(`Student ${fullName} registered successfully for Room ${roomNumber}!`);
            registerForm.reset();
        } else {
            alert('Please fill all the fields before submitting.');
        }
    });

    allocationList.addEventListener('click', function (e) {
        const target = e.target;
        const row = target.closest('tr');

        if (target.classList.contains('delete-btn')) {
            row.remove();
        }

        if (target.classList.contains('update-btn')) {
            const nameCell = row.querySelector('.student-name');
            const roomCell = row.querySelector('.room-number');

            const newName = prompt('Enter updated name:', nameCell.textContent);
            const newRoom = prompt('Enter updated room number:', roomCell.textContent);

            if (newName !== null && newRoom !== null) {
                nameCell.textContent = newName.trim();
                roomCell.textContent = newRoom.trim();
                alert('Student details updated successfully.');
            }
        }
    });
});
