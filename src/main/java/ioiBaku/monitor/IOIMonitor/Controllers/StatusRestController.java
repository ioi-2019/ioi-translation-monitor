package ioiBaku.monitor.IOIMonitor.Controllers;

import ioiBaku.monitor.IOIMonitor.Domains.Status;
import ioiBaku.monitor.IOIMonitor.Repositories.StatusRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class StatusRestController {

    private static final String CSV_SEPARATOR = ",";
    StatusRepository statusRepository;

    public StatusRestController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;

    }

    @RequestMapping("/status/printfinal/{countrycode}")
    public String changeStatusPrintFinal(@PathVariable(name="countrycode", required = true) String countryCode) {

                Status status = statusRepository.findByCountryCode(countryCode);
                statusRepository.changeStatus(status.getCountry(), "Printing FINAL");




        return "OK";

    }
    @RequestMapping("/status/translate/{countrycode}")
    public String changeStatusTranslate(@PathVariable(name="countrycode", required = true) String countryCode) {

        Status status = statusRepository.findByCountryCode(countryCode);
        statusRepository.changeStatus(status.getCountry(), "Translating");


        return "OK";

    }
    @RequestMapping("/status/printdraft/{countrycode}")
    public String changeStatusDraftPrint(@PathVariable(name="countrycode", required = true) String countryCode) {

        Status status = statusRepository.findByCountryCode(countryCode);
        statusRepository.changeStatus(status.getCountry(), "Printing DRAFT");


        return "OK";

    }



    @RequestMapping("/extra")
    @ResponseBody
    public String extra(@RequestParam(name="countrycode", required = true) String countrycode, @RequestParam(name="extra1", required = true) String extra1, @RequestParam(name="extra2", required = true) String extra2) {


            Status status = statusRepository.findByCountryCode(countrycode);


            if (extra1.toString().equals("NONE") && extra2.toString().equals("NONE")) {
                statusRepository.extra(status.getCountryCode(), "", "");
            } else if (extra2.toString().equals("NONE")) {
                statusRepository.extra(status.getCountryCode(), extra1.toString(), "");

            } else if (extra1.toString().equals("NONE")) {
                statusRepository.extra(status.getCountryCode(), "", extra2.toString());

            }else{
                statusRepository.extra(status.getCountryCode(), extra1.toString(), extra2.toString());

            }

        return "OK";

    }
    @RequestMapping("/extra/done")
    @ResponseBody
    public String extraDone(@RequestParam(name="countrycode", required = true) String countrycode, @RequestParam(name="extra", required = true) String extra) {

            Status status = statusRepository.findByCountryCode(countrycode);
            if (status.getExtra1().toString().equals(extra)) {
                statusRepository.extraDone(status.getCountryCode(), "");

            } else if (status.getExtra2().toString().equals(extra)) {
                statusRepository.extraDone1(status.getCountryCode(), "");
            }

        return "OK";

    }
    @RequestMapping("/export")
    public String writer() {
        Iterable<Status> all = statusRepository.findAll();
        Iterator<Status> iterator = all.iterator();
        List<Status> statusData = new ArrayList<>();
        while (iterator.hasNext()) {
            statusData.add(iterator.next());
        }

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Export.csv"), "UTF-8"));
            for (Status cd : statusData) {
                StringBuffer oneLine = new StringBuffer();

                oneLine.append(cd.getCountry());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getCountryCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getStatus());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getMessage());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getExtra1());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getExtra2());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cd.getDate());


                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


        return "Export is done SuccessFully";
    }

}
