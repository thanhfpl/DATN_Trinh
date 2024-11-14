package com.demo.service;

import com.demo.entity.Calichhen;
import com.demo.repositories.Calichhenrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaLichHenService {
    @Autowired
    private Calichhenrepository caLichHenRepository;


    @Transactional
    public void resetTrangThaiCa(LocalDate ngay) {
        // Bước 1: Đặt tất cả các ca về false
        caLichHenRepository.updateTrangThaiAll(false);

        // Bước 2: Đặt những ca đã được đặt trong ngày đó về true
        caLichHenRepository.updateTrangThaiCaDatThanhCong(ngay);
    }

    public void UpdateNgayNghi(LocalDate ngay){
        caLichHenRepository.updateNgayNghi(ngay);
    }

    public void addOrUpdate(Calichhen calichhen){
        caLichHenRepository.save(calichhen);
    }

    public Optional<Calichhen> findById (Integer id){
        return caLichHenRepository.findById(id)
                ;}

    public void DoiTrangthaiCaTrongNgay(int idCaLichHen, LocalDate ngay, boolean trangThai){
        caLichHenRepository.updateTrangThaiCaTrongNgay(idCaLichHen, ngay, trangThai);
    }

    public List<Calichhen> getAllByDate(LocalDate date){
        return caLichHenRepository.findAllCaAndStatusByDateaAndTrangthaiFalse(date);
    }

    public List<Calichhen> findAll(){
        return caLichHenRepository.findAll();
    }

    public boolean isCaAvailable(Integer caId,LocalDate date) {
        Optional<Calichhen> caOptional = caLichHenRepository.findById(caId);
        if (caOptional.isPresent()) {
            Calichhen ca = caOptional.get();

            // Lấy ngày hiện tại và thời gian hiện tại
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();

            // Kiểm tra nếu ca thuộc ngày hôm nay và thời gian của ca là trước giờ hiện tại thì không cho đặt
            if (date.isEqual(today) && ca.getThoigianca().isBefore(now)) {
                return false;
            }

            // Nếu ca không thuộc ngày hôm nay hoặc thời gian của ca là sau giờ hiện tại thì cho phép đặt
            return true;
        }

        // Nếu ca không tồn tại thì trả về false
        return false;
    }
}
