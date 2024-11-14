<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page pageEncoding="utf-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách lịch hẹn</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h1>Danh sách lịch hẹn</h1>
    <form action="#" th:action="@{/lich-hen/search}" method="get" class="mb-3">
        <div class="input-group">
            <input type="text" class="form-control" name="email" placeholder="Nhập email người đặt" th:value="${searchEmail}"/>
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">Tìm kiếm</button>
            </div>
        </div>
    </form>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Khách Hàng</th>
            <th>Thú Cưng</th>
            <th>Dịch Vụ</th>
            <th>Email Người Đặt</th>
            <th>Trạng Thái</th>
            <th>Thời Gian</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lichHenList}" var="lichhen">
            <tr>
                <td>${lichHen.idkhachhang}</td>
                <td>${lichHen.thucung.ten}</td>
                <td>${lichHen.dichvu.tendichvu}</td>
                <td>${lichHen.emailNguoiDat}</td>
                <td>
                    <select id="trangthai_${lichhen.id}" name="trangthai_${lichhen.id}">
                        <c:forEach var="state" items="${trangThaiList}">
                            <option value="${state}"
                                    <c:if test="${state == lichhen.trangthai}">selected</c:if>
                            >
                                <c:choose>
                                    <c:when test="${state == 0}">Chờ xử lý</c:when>
                                    <c:when test="${state == 1}">Đã xác nhận</c:when>
                                    <c:otherwise>Đã hoàn thành</c:otherwise>
                                </c:choose>
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>${lichHen.date}</td>
                <td>
                    <button type="button" id="updateBtn_${lichhen.id}" class="btn btn-success"
                            onclick="updateStatus(${lichhen.id})">Cập nhật</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        <a th:if="${page > 0}" th:href="@{/lich-hen/all(page=${page - 1})}" class="btn btn-secondary">Trước</a>
        <a th:if="${page < totalPages - 1}" th:href="@{/lich-hen/all(page=${page + 1})}" class="btn btn-secondary">Tiếp theo</a>
    </div>
</div>

<script>
    function updateStatus(id) {
        var trangthai = document.getElementById('trangthai_' + id).value;

        fetch('/lich-hen/update/' + id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'trangthai=' + trangthai
        })
            .then(response => {
                if (response.ok) {
                    alert("Cập nhật trạng thái thành công!");
                    location.reload(); // Tải lại trang để thấy cập nhật
                } else {
                    alert("Có lỗi xảy ra trong khi cập nhật!");
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
</script>
</body>
</html>