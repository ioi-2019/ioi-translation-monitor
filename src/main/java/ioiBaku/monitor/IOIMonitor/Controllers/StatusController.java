package ioiBaku.monitor.IOIMonitor.Controllers;

import ioiBaku.monitor.IOIMonitor.Domains.Status;
import ioiBaku.monitor.IOIMonitor.Repositories.StatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StatusController {
   StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;

    }


    @RequestMapping("/monitor")
    public String showAllStatus(Model model) {
        final String uri = "http://localhost:8080/export";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, String.class);

        List<Status> myList = new ArrayList<>();
        List<Status> myList2 = new ArrayList<>();
        List<Status> myList3 = new ArrayList<>();

        for(int i=1;i<=29;i++) {
            Status statusList = statusRepository.findById(i);
            myList.add(statusList);
        }
        for(int i = 30;i<=58;i++){
            Status statusList2 = statusRepository.findById(i);
            myList2.add(statusList2);
        }
        for(int i = 59;i<=87;i++){
            Status statusList3 = statusRepository.findById(i);
            myList3.add(statusList3);
        }


        model.addAttribute("statusList", myList);
        model.addAttribute("statusList2", myList2);
        model.addAttribute("statusList3", myList3);

            return "monitor";

    }

    @RequestMapping("/staff")
    public String forStaff(Model model) {
        List<Status> myList = new ArrayList<>();
        List<Status> myList2 = new ArrayList<>();
        List<Status> myList3 = new ArrayList<>();
        for(int i=1;i<=29;i++){
            Status statusList = statusRepository.findById(i);
            myList.add(statusList);
        }
        for(int i = 30;i<=58;i++){
            Status statusList2 = statusRepository.findById(i);
            myList2.add(statusList2);
        }
        for(int i = 59;i<=87;i++){
            Status statusList3 = statusRepository.findById(i);
            myList3.add(statusList3);
        }


        model.addAttribute("statusList", myList);
        model.addAttribute("statusList2", myList2);
        model.addAttribute("statusList3", myList3);


        return "staff";

    }
//    @RequestMapping("/monitor-print")
//    public String monitorPrint(Model model) {
//        List<Status> myList = new ArrayList<>();
//
//        Format formatter = new SimpleDateFormat("HH:mm");
//
////        for(int i =1; i<=87;i++) {
////            Status status = statusRepository.findById(i);
//            status.setDate(formatter.format(new Date()));
//            if (status.getStatus().toString().equals("Printing FINAL")||status.getStatus().toString().equals("Printing DRAFT")){
//
//                myList.add(status);
////        }
//
//    }
//
//        model.addAttribute("statusList", myList);
//        return "printing";
//
//    }

    @RequestMapping("/change/{id}/")
    public String ready(@PathVariable(name = "id", required = true) Integer id,@RequestParam(name = "status_select", required = true) String status_select,
                        @RequestParam(name = "status_text", required = true) String status_text) {
        Status statusData =statusRepository.findById(id);

        String status = status_select;

        if(status.isEmpty()) status = status_text;
        statusRepository.statusDropdown(statusData.getCountry(),status);



        return "redirect:/staff";

    }
    @RequestMapping("/message/{id}")
    public String call(@PathVariable(name="id", required = true) Integer id) {

        statusRepository.message(id,"Please Approach");
        return "redirect:/staff";
    }

    @RequestMapping("/unmessage/{id}")
    public String uncall(@PathVariable(name="id", required = true) Integer id) {

        statusRepository.message(id,"");
        return "redirect:/staff";
    }

    @RequestMapping("/monitor-extra")
    public String extra(Model model){
        List<Status> myList = new ArrayList<>();

        for(int i =1; i<=87;i++) {
    Status statusList = statusRepository.findById(i);
    if ((!statusList.getExtra1().isEmpty())||(!statusList.getExtra2().isEmpty())) {
        myList.add(statusList);

    }
}
        model.addAttribute("extra", myList);
        return "extra";

    }



}


