document.querySelector("#add-student").addEventListener("click", addStudent);
document.querySelector("#get-student").addEventListener("click", getStudentByLibret);
document.querySelector("#get-student-byGender").addEventListener("click", getStudentsByGender);
document.querySelector("#getCareers").addEventListener("click", getCareers);
document.querySelector("#inscription-button").addEventListener("click", registerStudent);
document.querySelector("#careers-report-btn").addEventListener("click", getReport);
document.querySelector("#get-student-carrer-city").addEventListener("click", getStudentByCareerCity);

function getAllStudent() {
	let bodyTable = document.getElementsByClassName('bodyTable')[0];
	axios.get('http://localhost:8080/ArquitecturaWebTp3/api/student')
		.then(function(response) {
			
			let elements = response['data'];
			showStudents(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		})
		.then(function() {
		});
}

function showStudents(bodyTable, elements) {
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['numBook']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['name']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['lastname']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['age']);
		cell4.appendChild(newText4);
		let cell5 = newRow.insertCell(4);
		let newText5 = document.createTextNode(element['gender']);
		cell5.appendChild(newText5);
		let cell6 = newRow.insertCell(5);
		let newText6 = document.createTextNode(element['numDoc']);
		cell6.appendChild(newText6);
		let cell7 = newRow.insertCell(6);
		let newText7 = document.createTextNode(element['cityResident']);
		cell7.appendChild(newText7);
	});
}

function registerStudent(){
	let career = document.querySelector("#inscription-career-list").value;
	let student = document.querySelector("#id-student").value;
	let date = moment().format('YYYY-MM-DD HH:mm:ss');
	if(career === "" || student === ""){
		alert("Todos los campos son requeridos");
	}
	let data = {
		 "signUpDate": date,
		 "idCareer": career,
		 "idStudent": student,
	}
	
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student/inscription', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
	.then(response => {
		getCareers();
	})
	.catch(function(error) {
			console.log(error);
	})
}


function addStudent() {
	let num = document.querySelector("#num-libret").value;
	let name = document.querySelector("#name").value;
	let lastname = document.querySelector("#lastname").value;
	let gender = document.querySelector("#gender").value;
	let age = document.querySelector("#age").value;
	let doc = document.querySelector("#num-doc").value;
	let city = document.querySelector("#city").value;

	if((num === "" || name === "")||(lastname === "" || age === "")||(doc === "" || city === "")){
			alert("Todos los campos son requeridos");
	}
	
	let data = {
		"numLibret": num,
		"name": name,
		"lastName": lastname,
		"gender": gender,
		"age": age,
		"numDoc": doc,
		"city": city
	}

	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			getAllStudent();
		})
		.catch(function(error) {
			console.log(error);
		})
	}

function getStudentByLibret() {
	let num = document.querySelector("#num-libret-getBy").value;
	let span = document.querySelector("#studentByLibret");
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student/' + num)
		.then(response => {
			return response.json()
		}).then(function(student) {
			span.innerHTML = "Numero de Libreta: " + student.numLibret + " Nombre: " + student.name +
				" Apellido: " + student.lastname + " Edad: " + student.age + " Genero: " + student.gender +
				" Ciudad: " + student.city;
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function getStudentsByGender() {
	let gender = document.querySelector("#gender-getBy").value;
	let bodyTable = document.getElementsByClassName('bodyTable')[1];
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student/gender/' + gender)
		.then(response => {
			return response.json()
		}).then(function(students) {
			let tableGender = document.getElementsByClassName('bodyTableGender')[0];
			tableGender.innerHTML = '';
			showStudents(bodyTable, students);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function getStudentByCareerCity() {
	let career = document.querySelector("#careerList").value;
	let city = document.querySelector("#cityList").value;
	let bodyTable = document.getElementsByClassName('bodyTable')[2];
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student/' + career + '/' + city)
		.then(response => {
			return response.json()
		}).then(function(students) {
			if (students.length === 0) {
				alert('No existen Alumnos de esa ciudad para la carrera seleccionada');	
			}
			let tableGender = document.getElementsByClassName('bodyTableStudentCareerCity')[0];
			tableGender.innerHTML = '';
			showStudents(bodyTable, students);
		})
		.catch(function(error) {
			console.log(error);
			alert('No existen Alumnos de esa ciudad para la carrera seleccionada');
		}

	);
}

function getCareersList(selectCareer) {
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/career/list')
		.then(response => {
			return response.json()
		}).then(function(careers) {
			let selectCareer = document.getElementById('careerList');
			for (const index in careers) {
				selectCareer.options[selectCareer.options.length] = new Option(careers[index]['name'], careers[index]['id']);
			}
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function getCities() {
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/student/cities')
		.then(response => {
			return response.json()
		}).then(function(cities) {
			let selectCities = document.getElementById('cityList');
			for (const index in cities) {
				selectCities.options[selectCities.options.length] = new Option(cities[index], cities[index]);
			}
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}


function getCareers() {
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/career')
		.then(response => {
			return response.json()
		}).then(function(elements) {
			let table = document.querySelector("#career-inscriptions");
			table.innerHTML = "";
			elements.forEach(element => {
				let newRow = table.insertRow(-1);
				let cell1 = newRow.insertCell(0);
				let newText1 = document.createTextNode(element['careerName']);
				cell1.appendChild(newText1);
				let cell2 = newRow.insertCell(1);
				let newText2 = document.createTextNode(element['inscriptionQuantity']);
				cell2.appendChild(newText2);
			})
		}).catch(function(error) {
			console.log(error);
		});
}

function getReport() {
	fetch('http://localhost:8080/tp3_ejercicio_integrador/rest/career/report')
		.then(response => {
			return response.json()
		}).then(function(elements) {
			let table = document.querySelector("#career-report");
			table.innerHTML = "";
			elements.forEach(element => {
				let newRow = table.insertRow(-1);
				let cell1 = newRow.insertCell(0);
				let newText1 = document.createTextNode(element['career']);
				cell1.appendChild(newText1);
				let cell2 = newRow.insertCell(1);
				let newText2 = document.createTextNode(element['year']);
				cell2.appendChild(newText2);
				let cell3 = newRow.insertCell(2);
				let newText3 = document.createTextNode(element['studentQuantity']);
				cell3.appendChild(newText3);
				let cell4 = newRow.insertCell(3);
				let newText4 = document.createTextNode(element['studentGraduated']);
				cell4.appendChild(newText4);
			})
		}).catch(function(error) {
			console.log(error);
		});
}

let selectCareer = document.getElementById('careerList');
getCareersList(selectCareer);
let careerForInscription = document.getElementById('inscription-career-list');
getCareersList(careerForInscription);
getCities();