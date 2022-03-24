<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="offset-3 col-6 mt-4">
	<form action="ChangePassword" method="post">
		<div class="card">
			<div class="card-header">
				<b>Change Password</b>
			</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="username">Username</label> <input type="text"
								class="form-control" name="username" id="username" value="${username}"
								aria-describedby="usernameHid" placeholder="username"> <small
								id="usernameHid" class="form-text text-muted">Username
								is required</small>
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="text"
								class="form-control" name="password" id="password"
								aria-describedby="passwordHid" placeholder="password"> <small
								id="passwordHid" class="form-text text-muted">Password
								is required</small>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label for="currentPassword">Current Password</label> <input
								type="password" class="form-control" name="currentPassword"
								id="currentPassword" placeholder="current password">
						</div>
						<div class="form-group">
							<label for="confirmPassword">Confirm password</label> <input
								type="password" class="form-control" name="confirmPassword"
								id="confirmPassword" placeholder="confirm password">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Change Password</button>
			</div>
		</div>
	</form>
</div>