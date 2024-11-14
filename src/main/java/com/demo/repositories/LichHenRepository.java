package com.demo.repositories;

import com.demo.entity.LichHen;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LichHenRepository extends JpaRepository<LichHen, Integer> {
//    List<LichHen> findByKhachHang_Username(String username); // Thêm underscore để chỉ liên kết
//    List<LichHen> findByDateBetween(Date startDate, Date endDate);
//    List<LichHen> findByTrangThai(Boolean trangThai);
//    List<LichHen> findByKhachHang_Id(Integer id); // Sử dụng khachHang ở đây

    Page<LichHen> findByIdkhachhang(String idKhachHang, Pageable pageable);

    Page<LichHen> findByEmailNguoiDat(String idKhachHang, Pageable pageable);

    boolean existsByDateAndIdcalichhen_Id(LocalDate date, int idCaLichHen);

    List<LichHen> findByEmailNguoiDatAndDateBefore(String emailNguoiDat, LocalDate date);

    Optional<LichHen> findByDateAndIdcalichhen_IdAndTrangthai(LocalDate date, int idCaLichHen, int trangthai);

    Page<LichHen> findByTrangthaicaAndDateAfter(boolean trangthaica, LocalDate date, Pageable pageable);
}
