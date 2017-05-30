import random

import MySQLdb
import requests

db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()

# urls=["http://www.tianyancha.com/search/%E7%BB%9F%E8%AE%A1.json?&pn="+str(i) for i in range(38,51)]
url = "https://xueqiu.com/stock/cata/stocklist.json?" \
      "page=%d"
UA = ["Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0",
      "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"]
cookies = ["aliyungf_tc=AQAAADvGpmLaDgsAb1Gi0/g/rjOa/iJj; s=fv120q5i4b; "
           "xq_a_token=876f2519b10cea9dc131b87db2e5318e5d4ea64f; "
           "xq_r_token=709abdc1ccb40ac956166989385ffd603ad6ab6f; "
           "u=651495873728701; __utma=1.335231814.1495873729.1495956256.1496024223.6; "
           "__utmc=1; __utmz=1.1495873729.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); "
           "Hm_lvt_1db88642e346389874251b5a1eded6e3=1495873729; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1496029396"]

headers = {
    "Accept": "application/json, text/javascript, */*; q=0.01",
    "Accept-Encoding": "gzip, deflate, sdch, br",
    "Accept-Language": "zh-CN,zh;q=0.8,en;q=0.6,en-US;q=0.4",
    "Cache-Control": "no-cache",
    "Connection": "keep-alive",
    "Cookie": cookies[0],
    "host": "xueqiu.com",
    "Pragma": "no-cache",
    "Referer": "https://xueqiu.com/hq",
    "User-Agent": random.choice(UA),
    "X-Requested-With": "XMLHttpRequest"
}


def get_data(url):
    print(url)
    data = requests.get(url=url, headers=headers).json()
    print data
    data = data["stocks"]
    # print(data)

    if len(data) == 0:
        return 0

    for i in range(len(data)):
        symbol = data[i]["symbol"]
        price = data[i]["current"]
        deviation = data[i]["change"]
        devRatio = data[i]["percent"]
        high = data[i]["high"]
        low = data[i]["low"]
        highFT = data[i]["high52w"]
        lowFT = data[i]["low52w"]
        totalValue = data[i]["marketcapital"]
        pe = data[i]["pettm"]
        dealAmount = data[i]["volume"]
        dealValue = data[i]["amount"]

        sql_save = """insert into dayK (symbol, price, deviation, devRatio, low, high, lowFT, highFT, totalValue, 
pe, dealAmount, dealValue) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
        cursor.execute(sql_save, (symbol, price, deviation, devRatio, low, high, lowFT, highFT, totalValue, pe,
                                  dealAmount, dealValue))
        db.commit()

    return 1

page = 1

while(1):
    pageUrl = (url % page) + "&size90&order=desc&orderby=percent&type=11%2C12&_=1496029395792"
    if get_data(pageUrl) == 0:
        break
    page += 1

cursor.close()
