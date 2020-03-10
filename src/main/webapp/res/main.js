let tr, td, editButton, deleteButton;
let content = document.getElementById ("content");
let saveButton = document.querySelector("#save");
let first_name = document.getElementById('first_name');
let last_name = document.getElementById('last_name');
let age = document.getElementById('age');
let birthdate = document.getElementById('birthdate');
let faculty = document.getElementById('faculty');
let requestURL = 'list';
let request = new XMLHttpRequest();

window.onload = contentLoad();

function contentLoad(){
	request.open('GET', requestURL);
	request.responseType = 'json';
	request.send();
	request.onload = function() {
		const students = request.response;
	    students.forEach(student => {
	    	tr = document.createElement('tr');
	    	content.appendChild(tr);
	    	
	    	td = document.createElement('td');
	    	td.innerHTML = student.firstName;
	    	tr.appendChild(td);
	    	
	    	td = document.createElement('td');
	    	td.innerHTML = student.lastName;
	    	tr.appendChild(td); 
	    	
	    	td = document.createElement('td');
	    	td.innerHTML = student.age;
	    	tr.appendChild(td);
	    	
	    	td = document.createElement('td');
	    	td.innerHTML = formatJSONDate(student.birthdate);
	    	tr.appendChild(td);
	    	
	    	td = document.createElement('td');
	    	td.innerHTML = student.faculty;
	    	tr.appendChild(td);
	    	
	    	td = document.createElement('td');
	    	editButton = document.createElement('button');
	    	editButton.setAttribute("class", "edit");
	    	editButton.innerHTML = 'Edit';
	    	td.appendChild(editButton);
	    	
	    	deleteButton = document.createElement('button');
	    	deleteButton.setAttribute("class", "delete");
	    	deleteButton.innerHTML = 'Delete';
	    	td.appendChild(deleteButton);
	    	tr.appendChild(td);
	    	
	    	editButton.addEventListener("click", function(){
	    		 toggleModal();
	    		 loadData(student);
	    	    });
	    	
	    	deleteButton.addEventListener("click", function(){
			 deleteStudent(student.id);
		    });
	    });
	}
}


function formatJSONDate(jsonDate) {
	 var newDate = new Date(jsonDate);
	 var curr_date = newDate.getDate();
	 var curr_month = newDate.getMonth() + 1; 
	 var curr_year = newDate.getFullYear();
	 return (curr_year + "-" + curr_month + "-" + curr_date );
}

var add = document.getElementById('modal_opener');
var xlsx = document.getElementById('xlsxExport');
var modal = document.querySelector('.modal');
var save = document.getElementById('save');

function attachModalListeners(modalElm) {
  modalElm.querySelector('.close_modal').addEventListener('click', toggleModal);
  modalElm.querySelector('#close').addEventListener('click', toggleModal);
  modalElm.querySelector('.overlay').addEventListener('click', toggleModal);
}

function detachModalListeners(modalElm) {
  modalElm.querySelector('.close_modal').removeEventListener('click', toggleModal);
  modalElm.querySelector('#close').removeEventListener('click', toggleModal);
  modalElm.querySelector('.overlay').removeEventListener('click', toggleModal);
}

function toggleModal() {
  var currentState = modal.style.display;

  if (currentState === 'none') {
    modal.style.display = 'block';
    attachModalListeners(modal);
  } else {
    modal.style.display = 'none';
    detachModalListeners(modal);
  }
}

add.addEventListener("click", function(){
	saveButton.setAttribute("student-id", null);
	document.querySelectorAll('input[type=text], textarea').forEach(el=>el.value = '');
	toggleModal();
});

saveButton.addEventListener("click", function(){
	toggleModal();
	saveStudent();
});


function xlsxExport() {
	fetch("list/export", {
		  method: 'POST'
		});
}

xlsx.addEventListener('click',function(){
	xlsxExport();
});

function loadData(data) {
	saveButton.setAttribute('student-id', data.id);
	first_name.value = data.firstName;
	last_name.value = data.lastName;
	age.value = data.age;
	birthdate.value = formatJSONDate(data.birthdate);
	faculty.value = data.faculty;
}

function deleteStudent(id) {
	fetch(requestURL + "/" + id, {
		  method: 'DELETE'
		});
	window.location.reload()
}

function saveStudent() {
	const formData = {
			firstName : first_name.value,
			lastName : last_name.value,
			age : age.value,
			birthdate : birthdate.value,
			faculty : faculty.value
	}
	
	if(saveButton.getAttribute('student-id') === 'null'){
		fetch (requestURL, {
			method: 'POST',
			headers: {'Content-Type' : 'application/json'},
			body: JSON.stringify(formData)});
	}else{
		fetch (requestURL + "/" + saveButton.getAttribute('student-id'), {
			method: 'PUT',
			headers: {'Content-Type' : 'application/json'},
			body: JSON.stringify(formData)});
	}
	window.location.reload()
}