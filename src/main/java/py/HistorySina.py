# coding: utf-8
import MySQLdb
import requests
from lxml import etree, html

db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()


def get_data(url, symbol):
    content = requests.get(url)

    tree = etree.HTML(content.text)

    count = 2

    sql_insert = 'insert into dayK (symbol, dataTime, `open`, high, `close`, low, dealAmount, dealValue) ' \
                 'values (%s, %s, %s, %s, %s, %s, %s, %s)'

    while 1:
        nodes = tree.xpath('//*[@id="FundHoldSharesTable"]/tr[%d]' % count)

        if len(nodes) == 0:
            break

        node = nodes[0]

        date = str.strip(node.xpath('td[1]/div/a/text()')[0])
        open = str.strip(node.xpath('td[2]/div/text()')[0])
        high = str.strip(node.xpath('td[3]/div/text()')[0])
        close = str.strip(node.xpath('td[4]/div/text()')[0])
        low = str.strip(node.xpath('td[5]/div/text()')[0])
        dealAmount = str.strip(node.xpath('td[6]/div/text()')[0])
        dealValue = str.strip(node.xpath('td[7]/div/text()')[0])

        print sql_insert % (symbol, date, open, high, close, low, dealValue, dealAmount)

        cursor.execute(sql_insert, (symbol, date, open, high, close, low, dealValue, dealAmount))
        db.commit()

        count += 1

    # //*[@id="FundHoldSharesTable"]/tbody/tr[39] //*[@id="FundHoldSharesTable"]/tbody/tr[2]/td[1]/div

    # // *[ @ id = "FundHoldSharesTable"] / tbody / tr[2]


get_data("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/601212.phtml?year=2017&jidu=2",
         'SH601212')