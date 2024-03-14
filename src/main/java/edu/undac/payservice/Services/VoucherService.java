package edu.undac.payservice.Services;

import edu.undac.payservice.Web.Requests.FileRequest;
import edu.undac.payservice.Models.Voucher;
import edu.undac.payservice.Web.Responses.VoucherResponse;
import edu.undac.payservice.Persistence.Mappers.VoucherResponseMapper;
import edu.undac.payservice.Persistence.Repositories.VoucherCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService {

    @Autowired
    private VoucherCrudRepository voucherCrudRepository;

    @Autowired
    private ConceptoService conceptoService;

    @Autowired
    private VoucherResponseMapper voucherResponseMapper;

    public List<VoucherResponse> findByIdAlumno(String idAlumno){
        List<VoucherResponse> voucherResponse = new ArrayList<>();
        for (Voucher voucher : voucherCrudRepository.findByIdAlumno(idAlumno)) {
            voucherResponse.add(voucherResponseMapper.toVoucherResponse(voucher,
                    conceptoService.getByCodigo(voucher.getConceptoId())));
        }
        return voucherResponse;
    }

    public List<VoucherResponse> findByIdVoucher(String idVoucher){
        List<VoucherResponse> voucherResponse = new ArrayList<>();
        for (Voucher voucher : voucherCrudRepository.findByIdVoucher(idVoucher)) {
            voucherResponse.add(voucherResponseMapper.toVoucherResponse(voucher,
                    conceptoService.getByCodigo(voucher.getConceptoId())));
        }
        return voucherResponse;
    }

    public List<VoucherResponse> findByAlumnoAndConcepto(String idAlumno, int concepto){
        List<VoucherResponse> voucherResponse = new ArrayList<>();
        for (Voucher voucher : voucherCrudRepository.findByAlumnoAndConcepto(idAlumno, concepto)) {
            voucherResponse.add(voucherResponseMapper.toVoucherResponse(voucher,
                    conceptoService.getByCodigo(voucher.getConceptoId())));
        }
        return voucherResponse;
    }

    public void updateVoucher(int id){
        voucherCrudRepository.updateVoucher(id);
    }

    public void insertVoucher(Voucher voucher){
        voucherCrudRepository.insertVoucher(voucher);
    }

    public void insertFromFiles(FileRequest files){
        if (files.fileOrDirectory() == null){
            mappingFile(files.getPath());
        } else {
            for (int i = 0; i < files.fileOrDirectory().length; i++){
                mappingFile(files.fileOrDirectory()[i].toString());
            }
        }
    }

    private void mappingFile(String pathFile){
        Voucher voucher = new Voucher();
        try {
            //Prepara la lectura de datos
            FileReader readerFile = new FileReader(pathFile);
            BufferedReader reader = new BufferedReader(readerFile);
            String lineFile = reader.readLine();
            //Extraer fecha
            String date = lineFile.substring((lineFile.length()-10), lineFile.length());
            voucher.setFecha(Voucher.dateConstructor(date));
            //Extraer demas datos
            while ((lineFile = reader.readLine()) != null){
                lineFile = lineFile.trim(); //elimina espacios iniciales
                if (lineFile.length() > 20) {
                    //encuentra lineas donde exista codigos de estudiante
                    if (lineFile.matches(".*\\b\\d{10,15}\\b.*")) {
                        //separa la linea en string separados por 2 a mas espacios en blanco
                        String[] line = lineFile.split("\\s{2,}");
                        //se guarda codigo, concepto, monto y estado
                        voucher.setCodigoVoucher(line[1]);
                        voucher.setConceptoId(Integer.valueOf(line[2]));
                        voucher.setMonto(Double.valueOf(line[line.length-2].replace(",", "")));
                        voucher.setEstado(0);
                        //validacion para guardar nombre y codigo estudiante
                        if (line.length == 11){
                            voucher.setCodigoEstudiante(line[0]);
                            voucher.setNombreEstudiante(line[7]);
                        } else if (line.length == 12 && line[6].matches("[0-9]{15}")) {
                            if (line[0].matches("0{15}")) {
                                voucher.setCodigoEstudiante(line[6].substring((line[6].length() - 8), line[6].length()));
                                voucher.setNombreEstudiante(line[8]);
                            } else {
                                voucher.setCodigoEstudiante(line[0]);
                                voucher.setNombreEstudiante(voucher.nameConstructor(line, 7, (line.length - 5)));
                            }
                        } else {
                            if (line[0].matches("0{15}")) {
                                voucher.setCodigoEstudiante(line[6].substring((line[6].length() - 8), line[6].length()));
                                voucher.setNombreEstudiante(voucher.nameConstructor(line, 8, (line.length - 5)));
                            } else {
                                voucher.setCodigoEstudiante(line[0]);
                                voucher.setNombreEstudiante(voucher.nameConstructor(line, 7, (line.length - 5)));
                            }
                        }
                        insertVoucher(voucher);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


















