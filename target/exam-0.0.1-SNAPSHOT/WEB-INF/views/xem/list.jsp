<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3>Danh Sách Dịch Vụ</h3>
<table border="1">
    <thead>
    <tr>
        <th>Tên Dịch Vụ</th>
        <th>Đơn Giá</th>
        <th>Đơn Vị Tiền</th>
        <th>Tên Loại Dịch Vụ</th>
        <th>Mô Tả Loại Dịch Vụ</th>
        <th colspan="2">Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="dv">
        <tr>
            <td>${dv.ten_dich_vu}</td>
            <td>${dv.don_gia}</td>
            <td>${dv.don_vi_tien}</td>
            <td>${dv.loaiDichVu.ten_loai_dich_vu}</td>
            <td>${dv.loaiDichVu.mo_ta}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form:form method="post" modelAttribute="dv">
    <div>
        <p>Tên:
        <form:input path="ten_dich_vu"></form:input></p>
    </div>
    <div>
        <p>Đơn Vị Tiền
        <form:input path="don_vi_tien"></form:input></p>
    </div>
    <div>
        <p>Đơn Giá
        <form:input path="don_gia"/>
       </p>
    </div>
    <div>
        <p>Tên Loại DV
        <form:select path="loaiDichVu">
            <form:option value="">------</form:option>
            <form:options itemValue="id" itemLabel="ten_loai_dich_vu" items="${dvloai}"></form:options>
        </form:select></p>
    </div>
    <button>Lưu Lại</button>
</form:form>