<html lang="vi">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <%@ page pageEncoding="utf-8"%>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Lịch Hẹn</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"> <!-- Thêm font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin:0;
            padding:20px;
        }

        .navbar {
            background-color: #00aaff;
            color: white;
            padding:15px;
            text-align: center;
        }

        .navbar a {
            color: white;
            margin:15px; /* Sửa lại để có khoảng cách đồng đều */
            text-decoration: none;
        }

        .container {
            display: flex;
            margin-top:20px;
        }

        .calendar,
        .appointment-list {
            width:100%;
            background: white;
            padding:20px;
            border-radius:8px;
            box-shadow:2px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
        }

        .appointment-item {
            padding:10px;
            border-bottom:1px solid #eee;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .create-appointment {
            margin-top:20px;
            padding:15px;
            background: #00aaff;
            color: white;
            border: none;
            border-radius:5px;
            cursor: pointer;
            width:100%;
        }

        .form-group {
            margin-bottom:15px;
        }

        .form-group input,
        .form-group textarea {
            width:100%;
            padding:10px;
            border:1px solid #ddd;
            border-radius:5px;
        }

        .delete-button {
            background: #ff4d4d;
            color: white;
            border: none;
            border-radius:4px;
            cursor: pointer;
            padding:5px 10px;
        }

        /*.table {*/
        /*    width: 100%;*/
        /*    border-collapse: collapse;*/
        /*    margin: 20px 0;*/
        /*    font-size: 18px;*/
        /*    text-align: left;*/
        /*}*/

        /*.table-striped tbody tr:nth-child(odd) {*/
        /*    background-color: #f9f9f9;*/
        /*}*/

        /*.table th, .table td {*/
        /*    padding: 12px;*/
        /*    border: 1px solid #ddd;*/
        /*}*/

        /*.table th {*/
        /*    background-color: #4CAF50;*/
        /*    color: white;*/
        /*}*/

        /*.table a.tbn-delete {*/
        /*    color: red;*/
        /*    text-decoration: none;*/
        /*    transition: color 0.3s ease;*/
        /*}*/

        /*.table a.tbn-delete:hover {*/
        /*    color: darkred;*/
        /*}*/

        /*.table tbody tr:hover {*/
        /*    background-color: #f1f1f1;*/
        /*}*/
    </style>
</head>

<body>

<div class="navbar">
    <a href="#">Lịch Hẹn</a>
    <a href="#">Tạo Lịch Hẹn</a>
    <a href="#">Lịch Sử</a>
    <a href="#">Cài Đặt</a>
</div>

<div class="container">
<%--    <div class="calendar">--%>
<%--        <h2>Lịch</h2>--%>
<%--        <!-- Thêm lịch ở đây. Sử dụng thư viện JavaScript để tạo lịch nếu cần. -->--%>
<%--        <p>Răng mà mặt xỉu xỉu rứa...</p>--%>
<%--    </div>--%>

    <div class="appointment-list">
        <h2>Danh sách lịch hẹn</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Khách Hàng</th>
                        <th>Thú Cưng</th>
                        <th>Dịch Vụ</th>
                        <th>Ngày Tháng</th>
                        <th>Trạng Thái</th>
                        <th colspan="1">Hành Động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="ql">
                        <tr>
                            <td>${ql.taiKhoan.username}</td>
                            <td>${ql.thuCung.tenCho}</td>
                            <td>${ql.dichVu.tenDichVu}</td>
                            <td>${ql.date}</td>
                            <td>${ql.trangThai}</td>
                            <td><a href="#" class="btn btn-danger btn-sm tbn-delete">Xoá</a> </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
<%--        <table class="table table-striped">--%>
<%--            <thead class="thead-dark">--%>
<%--            <tr>--%>
<%--                <th>Khách Hàng</th>--%>
<%--                <th>Thú Cưng</th>--%>
<%--                <th>Dịch Vụ</th>--%>
<%--                <th>Ngày Tháng</th>--%>
<%--                <th>Trạng Thái</th>--%>
<%--                <th>Hành Động</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach items="${list}" var="ql">--%>
<%--                <tr>--%>
<%--                    <td>${ql.taiKhoan.username}</td>--%>
<%--                    <td>${ql.thuCung.tenCho}</td>--%>
<%--                    <td>${ql.dichVu.tenDichVu}</td>--%>
<%--                    <td>${ql.date}</td>--%>
<%--                    <td>${ql.trangThai}</td>--%>
<%--                    <td><a href="#" class="btn btn-danger btn-sm tbn-delete">Xoá</a></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>

    </div>
    <!-- Thêm các lịch hẹn khác ở đây -->
</div>
</div>

<div class="create-appointment">
    <h3>Tìm kiếm lịch hẹn</h3>
    <div class="form-group">
        <input type="text" placeholder="Khách hàng" required>
    </div>
    <div class="form-group">
        <input type="datetime-local" required>
    </div>
    <div class="form-group">
        <textarea rows="3" placeholder="Mô tả"></textarea>
    </div>
    <button class="create-appointment-button">Lưu lịch hẹn</button> <!-- Thay cái tên lớp -->
</div>

</body>

</html>

<%--<table class="table table-striped">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>Khách Hàng</th>--%>
<%--        <th>Thú Cưng</th>--%>
<%--        <th>Dịch Vụ</th>--%>
<%--        <th>Ngày Tháng</th>--%>
<%--        <th>Trạng Thái</th>--%>
<%--        <th colspan="1">Hành Động</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <c:forEach items="${list}" var="ql">--%>
<%--        <tr>--%>
<%--            <td>${ql.taiKhoan.username}</td>--%>
<%--            <td>${ql.thuCung.tenCho}</td>--%>
<%--            <td>${ql.dichVu.tenDichVu}</td>--%>
<%--            <td>${ql.date}</td>--%>
<%--            <td>${ql.trangThai}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>