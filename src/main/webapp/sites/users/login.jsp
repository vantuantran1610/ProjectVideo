<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="offset-4 col-4">
	<form action="" method="post">
		<div class="card">
			<div class="card-header">
				<b>Login to System</b>
			</div>
			<div class="card-body">
				<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="form-group">
					<label for="username">Username</label> <input type="text"
						class="form-control" name="username" id="username"
						aria-describedby="usernameHid" placeholder="username"> <small
						id="usernameHid" class="form-text text-muted">Username is
						required</small>
				</div>
				<div class="form-group">
					<label for="passwrord">Password</label> <input type="text"
						class="form-control" name="password" id="password"
						aria-describedby="passwordHid" placeholder="password"> <small
						id="passwordHid" class="form-text text-muted">Password is
						required</small>
				</div>
				<div class="form-check form-inline">
					<label><input type="checkbox" class="form-check-input"
						name="remember">Remember me</label>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Login</button>
			</div>
		</div>
	</form>
</div>