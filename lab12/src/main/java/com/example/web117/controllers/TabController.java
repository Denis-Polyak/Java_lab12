package com.example.web117.controllers;

import com.example.web117.beans.TabulationBean;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TabController {
    private TabulationBean tabulationBean;

    @PostMapping("tabulate")
    public String tabulate(@RequestParam double start, @RequestParam double end, @RequestParam double step, @RequestParam double a, Model model){
        tabulationBean.fillPoints(start, end, step, a);
        int stepss = tabulationBean.steps(end,start,step);
        double p = 3.14;
        double [] arr = tabulationBean.massiveX(stepss, start, step);
        double [] ara = tabulationBean.massiveY (arr, a, p);
        model.addAttribute("points", tabulationBean.getPoints());
        model.addAttribute("steps", stepss);
        model.addAttribute("maxiY", tabulationBean.getMaxY(ara));
        model.addAttribute("miniY", tabulationBean.getMinY(ara));
        model.addAttribute("maxiX", tabulationBean.getMaxX(ara,arr));
        model.addAttribute("miniX", tabulationBean. getMinX(ara,arr));
        model.addAttribute("suma", tabulationBean.sum(ara));
        model.addAttribute("arifmetic", tabulationBean.arifm(ara));
        return "tabulation";
    }
}
