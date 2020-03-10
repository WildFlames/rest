<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rest API</title>
<link rel="stylesheet" href="/students/res/main.css">
<style>
</style>
</head>
<body>
	<div id="students">
		<button id="modal_opener">+ Add new student</button>
		<button id="xlsxExport">Export into xlsx</button>
		<table class="table_dark">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Birthday</th>
					<th>Faculty</th>
					<th>Action</th>
				</tr>
			<thead>
			<tbody id="content">
			</tbody>
		</table>
	</div>

	

	<div class="modal" style="display: none">
		<div class="overlay"></div>
		<div class="modal_content">
			<button title="Close" class="close_modal">
				<img src="https://img.icons8.com/ios-filled/26/000000/delete-sign.png">
			</button>
			<div class="group">
				<input id="first_name" type="text" placeholder="First Name">
			</div>
			<div class="group">
				<input id="last_name" type="text" placeholder="Last Name">
			</div>
			<div class="group">
				<input id="age" type="text" placeholder="Age">
			</div>
			<div class="group">
				<input id="birthdate" type="text" placeholder="Birthdate">
			</div>
			<div class="group">
				<input id="faculty" type="text" placeholder="Faculty">
			</div>
			<div class="actions">
			<button id="save">Save</button>
			<button id="close">Close</button>
			</div>
		</div>
	</div>
	<script src="/students/res/main.js"></script>
</body>
</html>