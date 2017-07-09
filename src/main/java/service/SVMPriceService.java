package service;

import model.DayK;
import model.SVM;

import java.util.List;

/**
 * Created by christine on 2017/7/8.
 */
public interface SVMPriceService {
    public List<SVM> predictByOCHL_List(String stockId);
    public double predictByOCHL_Tomorrow(String stockId);
}
