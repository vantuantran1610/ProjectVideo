<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <div class="col mt-4">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="videoediting-tab" data-toggle="tab" href="#videoediting" role="tab"
                        aria-controls="videoediting" aria-selected="true">User Editing</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="videolist-tab" data-toggle="tab" href="#videolist" role="tab"
                        aria-controls="videolist" aria-selected="false">User List</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="videoediting" role="tabpanel" aria-labelledby="videoediting-tab">
                    <form action="" method="post">
                        <div class="card">
                            <div class="card-body">
                               <div class="row">
                                   <div class="col">
                                        <div class="form-group">
                                          <label for="username">Username</label>
                                          <input type="text" class="form-control" name="username" id="username" aria-describedby="usernameHid" placeholder="username">
                                          <small id="usernameHid" class="form-text text-muted">Username is required</small>
                                        </div>
                                        <div class="form-group">
                                          <label for="fullname">Fullname</label>
                                          <input type="text" class="form-control" name="fullname" id="fullname" aria-describedby="fullnameHid" placeholder="fullname">
                                          <small id="fullnameHid" class="form-text text-muted">Fullname is required</small>
                                        </div>
                                   </div>
                                   <div class="col">
                                        <div class="form-group">
                                          <label for="password">Password</label>
                                          <input type="password" class="form-control" name="password" id="password" aria-describedby="passwordHid" placeholder="password">
                                          <small id="passwordHid" class="form-text text-muted">Password is required</small>
                                        </div>
                                        <div class="form-group">
                                          <label for="email">Email</label>
                                          <input type="text" class="form-control" name="email" id="email" aria-describedby="emailHid" placeholder="email">
                                          <small id="emailHid" class="form-text text-muted">Email is required</small>
                                        </div>
                                   </div>
                               </div>
                            </div>
                            <div class="card-footer text-muted">
                                <button class="btn btn-primary">Create</button>
                                <button class="btn btn-warning">Update</button>
                                <button class="btn btn-danger">Delete</button>
                                <button class="btn btn-info">Reset</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="videolist" role="tabpanel" aria-labelledby="videolist-tab">
                    <table class="table table-stripe">
                        <tr>
                            <td>Username</td>
                            <td>Fullname</td>
                            <td>Email</td>
                            <td>Role</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>tuantran</td>
                            <td>TranVanTuan</td>
                            <td>tuan@gmail.com</td>
                            <td>Admin</td>
                            <td>
                                <a href=""><i class="fa fa-edit" aria-hidden="true">Edit</i></a>
                                <a href=""><i class="fa fa-trash" aria-hidden="true">Delete</i></a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
           </div>