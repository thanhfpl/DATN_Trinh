package com.demo.controller;



import com.demo.DTO.DoiLichDTO;
import com.demo.entity.LichHen;
import com.demo.repositories.LichHenRepository;
import com.demo.service.LichHenService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lich-hen")
public class LichHenController {
    @Autowired
    public LichHenService lichHenService;

//    @PreAuthorize("hasRole('admin')")

    @GetMapping("/lich-hen")
    public String showLichHen(Model model) {
        model.addAttribute("lichHenList", lichHenService.getAllLich());
        model.addAttribute("trangThaiList", Arrays.asList(0, 1, 2)); // Thêm danh sách trạng thái
        return "lich-hen-views/lich_hen_list"; // Tên của template HTML
    }
    @GetMapping("/all")
    public String getAllLichHen(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<LichHen> lichHenPage = lichHenService.getAllPageLichHen(pageable);
        model.addAttribute("lichHenList", lichHenPage.getContent());
        model.addAttribute("page", lichHenPage.getNumber());
        model.addAttribute("totalPages", lichHenPage.getTotalPages());
        return "lich-hen-views/lich_hen_list"; // Trả về mẫu HTML
    }

    @GetMapping("/search")
    public String searchLichHen(@RequestParam String email, @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<LichHen> lichHenPage = lichHenService.findByEmailNguoiDat(email, pageable);
        model.addAttribute("lichHenList", lichHenPage.getContent());
        model.addAttribute("page", lichHenPage.getNumber());
        model.addAttribute("totalPages", lichHenPage.getTotalPages());
        model.addAttribute("searchEmail", email);
        return "list-lich-hen"; // Trả về mẫu HTML
    }

    @GetMapping("/update/{id}")
    public String updateLichHen(@PathVariable Integer id, Model model) {
        LichHen lichHen = lichHenService.findById(id);
        if (lichHen != null) {
            model.addAttribute("lichHen", lichHen);
            return "update-lich-hen"; // Mẫu cập nhật trạng thái
        }
        return "redirect:/lich-hen/all"; // Quay lại danh sách nếu không tìm thấy
    }

    @PostMapping("/update/{id}")
    public String updateLichHen(@PathVariable Integer id, @RequestParam Integer trangthai) {
        lichHenService.updateTrangThai(id, trangthai);
        return "redirect:/lich-hen/all"; // Quay lại danh sách sau khi cập nhật
    }


    @GetMapping("/all-data")
    public Page<LichHen> getAllLichHen(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10); // 10 items per page
        return lichHenService.getAllPageLichHen(pageable);
    }

//    @PreAuthorize("hasRole('user')")
//    @GetMapping("/findByIdUser")
//    public Page<LichHen> findByIdUser(@RequestParam(defaultValue = "0") int page) {
//        Pageable pageable = PageRequest.of(page, 10); // 10 items per page
//
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String idUser = authentication.getName(); // Đây là idUser
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////              String idUser = authentication.getName;
//        return lichHenService.findByIdUser(pageable,idUser);
//    }

//    @PreAuthorize("hasAnyRole('admin', 'manager')")
//    @GetMapping("/findByUserEmail")
//    public Page<LichHen> findByUserEmail(@RequestParam(defaultValue = "0") int page,@RequestParam("email") String Email) {
//        Pageable pageable = PageRequest.of(page, 10);
//
//        return lichHenService.findByEmailNguoiDat(pageable, Email);
//    }
//
//    @PreAuthorize("hasAnyRole('admin', 'manager')")
//    @GetMapping("/getListDoiTrangThai")
//    public Page<LichHen> listDoi(@RequestParam(defaultValue = "0") int page){
//        Pageable pageable = PageRequest.of(page, 10);
//        Page<LichHen> ListDoi = lichHenService.findAllLichWithTrangThai(pageable,true, LocalDate.now());
//        return ListDoi;
//    }
//
//    @PreAuthorize("hasAnyRole('admin', 'manager')")
//    @PutMapping("/updateTrangThai/{id}/{idTT}")
//    public ResponseEntity<LichHen> updateMore(@PathVariable int id, @PathVariable int idTT) {
//
//        // Tìm lịch hẹn
//        LichHen datLaiLich = lichHenService.findById(id);
//        if (datLaiLich == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy lịch hẹn
//        }
//
//        if (lichHenService.isCaTrungTrongNgay(datLaiLich.getDate(), datLaiLich.getIdcalichhen().getId())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Trả về lỗi nếu trùng ca
//        }
//
//        // Cập nhật trạng thái
//        datLaiLich.setTrangthai(idTT);
//        LichHen updateLich = lichHenService.addOrUpdate(datLaiLich);
//
//        return new ResponseEntity<>(updateLich, HttpStatus.OK); // Trả về 200 OK
//    }
//
//    @PreAuthorize("hasAnyRole('admin', 'manager')")
//    @PutMapping("/update-time/{id}")
//    public ResponseEntity<?> doiTimeQuyenAdmin(@PathVariable Integer id,@Valid @RequestBody DoiLichDTO doiLichDTO) {
//        LichHen lichhen = lichHenService.findById(id);
//        LichHen lichhenNew = new LichHen();
//
//        if (lichhen == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lịch hẹn không tồn tại.");
//        }
//
//        if (lichhen != null && lichhen.getTrangthai() == 4) {
////          Thay đổi thời gian và ca lịch
//            Optional<LichHen> lichhenDoiOptional = lichHenService.getLichHenByDateandCa(doiLichDTO.getDate(),doiLichDTO.getIdcalichhen());
//            if (!lichhenDoiOptional.isPresent()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lịch lỗi.");
//            }
//
//            LichHen lichDoi = lichhenDoiOptional.get();
//            lichDoi.setEmailNguoiDat(lichhen.getEmailNguoiDat());
//            lichDoi.setIdkhachhang(lichhen.getIdkhachhang());
//            lichDoi.setTrangthai(3); // Đặt trạng thái là "Chờ thanh toán"
//            lichDoi.setThoigianthaydoi(LocalDateTime.now());
//            lichDoi.setThucung(lichhen.getThucung());
//            lichDoi.setDichvu(lichhen.getDichvu());
//            lichDoi.setTrangthaica(true);
//            lichDoi.setSolanthaydoi(lichhen.getSolanthaydoi());
//            lichHenService.addOrUpdate(lichDoi);
//
////            Cập nhập số lần thay đổi
//            lichhen.setSolanthaydoi(lichhen.getSolanthaydoi()+1);
//
////            Tạo bản ghi lưu trừ lịch đổi
//            lichhenNew.setEmailNguoiDat(lichhen.getEmailNguoiDat());
//            lichhenNew.setIdkhachhang(lichhen.getIdkhachhang());
//            lichhenNew.setTrangthai(1); // Đặt trạng thái là "Thất bại"
//            lichhenNew.setIdcalichhen(lichhen.getIdcalichhen());
//            lichhenNew.setThoigianthaydoi(LocalDateTime.now());
//            lichhenNew.setThucung(lichhen.getThucung());
//            lichhenNew.setDichvu(lichhen.getDichvu());
//            lichhenNew.setDate(lichhen.getDate());
//            lichhenNew.setTrangthaica(true);
//            lichhenNew.setSolanthaydoi(lichhen.getSolanthaydoi());
//            lichHenService.addOrUpdate(lichhenNew);
//
//            lichhen.setTrangthai(5);
//            lichhen.setEmailNguoiDat("default-email@example.com");
//            if (lichhen.getTrangthaica()){
//                lichhen.setTrangthaica(false);
//            }else {
//                return ResponseEntity.ok("Lỗi ca");
//            }
//            lichHenService.addOrUpdate(lichhen);
//            return ResponseEntity.ok("Thời gian của lịch hẹn đã được cập nhật.");
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
