<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col mt-4">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item"><a class="nav-link active"
			id="videoEditing-tab" data-toggle="tab" href="#videoEditing"
			role="tab" aria-controls="videoEditing" aria-selected="true">Video
				Editing</a></li>
		<li class="nav-item"><a class="nav-link" id="videolist-tab"
			data-toggle="tab" href="#videolist" role="tab"
			aria-controls="videolist" aria-selected="false">Video List</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditing"
			role="tabpanel" aria-labelledby="videoEditing-tab">
			<form action="" method="post" enctype="multipart/form-data">
				<div class="card">
					<div class="card-body">
					<jsp:include page="/common/inform.jsp"></jsp:include>
						<div class="row">
							<div class="col-3">
								<div class="border p-3">
									<img src="${video.poster !=null?video.poster: 'images/destop.jpg' }" alt="" class="img-fluid">					
									<div class="input-group mb-3 mt-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Upload</span>
										</div>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="cover" name="cover" />
											<label for="cover">Choose file</label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-9">
								<div class="form-group">
									<label for="youtubeId">Youtube ID</label> <input type="text"
										class="form-control" name="videoId" id="youtubeId" value="${video.videoId}"
										aria-describedby="youtubeHid" placeholder="youtubeId">
									<small id="youtubeHid" class="form-text text-muted">Youtube
										id is required</small>
								</div>
								<div class="form-group">
									<label for="videoTitle">Video Title</label> <input type="text"
										class="form-control" name="title" id="videoTitle" value="${video.title}"
										aria-describedby="videoTitle" placeholder="videoTitle">
									<small id="videoTitleH" class="form-text text-muted">Video
										title is required</small>
								</div>
								<div class="form-group">
									<label for="viewCount">View Count</label> <input type="text"
										class="form-control" name="views" id="viewCount" value="${video.views}"
										aria-describedby="viewCountHid" placeholder="viewCount">
									<small id="viewCountHid" class="form-text text-muted">View
										count is required</small>
								</div>
								<div class="form-check form-check-inline">
									<label><input type="radio" class="form-check-input"
										value="true" name="active" id="status"  ${video.active? 'checked':'' }>Active</label> <label><input
										type="radio" class="form-check-input" value="false" ${! video.active? 'checked':'' }
										name="active" id="status">Inactive</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="description">Description</label>
								<textarea name="description" id="status" cols="30" rows="10"
									class="form-control">${video.description}</textarea>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" formaction="Admin/VideosManagement/create">Create</button>
						<button class="btn btn-warning" formaction="Admin/VideosManagement/update">Update</button>
						<button class="btn btn-danger" formaction="Admin/VideosManagement/delete">Delete</button>
						<button class="btn btn-info" formaction="Admin/VideosManagement/reset">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videolist" role="tabpanel"
			aria-labelledby="videolist-tab">
			<table class="table table-stripe">
				<tr>
					<td>Youtube ID</td>
					<td>Video Title</td>
					<td>View Count</td>
					<td>Status</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${videos}">
					<tr>
					<td>${item.videoId}</td>
					<td>${item.title}</td>
					<td>${item.views}</td>
					<td>${item.active? 'Active': 'Deactive'}</td>
					<td><a href="Admin/VideosManagement/edit?videoId=${item.videoId}"><i class="fa fa-edit mr-3"
							aria-hidden="true">Edit</i></a> <a href="Admin/VideosManagement/delete?videoId=${item.videoId}"><i class="fa fa-trash"
							aria-hidden="true">Delete</i></a></td>
				</tr>
					
				</c:forEach>
			</table>
		</div>
	</div>
</div>