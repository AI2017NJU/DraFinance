# coding: utf-8
import MySQLdb
import requests
import time
from lxml import etree, html

db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()

now = time.strftime('%Y-%m-%d', time.localtime(time.time()))


def get_insert_data(url, symbol):
    # print url
    content = requests.get(url).text
    raw = content.split(',')
    if len(raw) < 30:
        # data = [symbol, now, "null", "null", "null", "null", "null", "null"]
        data = [symbol, now, None, None, None, None, None, None]
    else:
        data = [symbol, raw[30], raw[1], raw[4], raw[2], raw[5], raw[8], raw[9]]
    return data

    # //*[@id="FundHoldSharesTable"]/tbody/tr[39] //*[@id="FundHoldSharesTable"]/tbody/tr[2]/td[1]/div

    # // *[ @ id = "FundHoldSharesTable"] / tbody / tr[2]


def get_stock():
    cursor.execute('select symbol from stockInfo')
    symbols = list(cursor.fetchall())

    url = 'http://hq.sinajs.cn/list=%s'

    sql_insert = 'insert into dayK (symbol, dataTime, `open`, high, `close`, low, dealAmount, dealValue) ' \
                 'values (%s, %s, %s, %s, %s, %s, %s, %s)'

    data_list = []

    for symbolPair in symbols:
        symbol = symbolPair[0]
        print get_insert_data(url % symbol.lower(), symbol)
        data_list.append(get_insert_data(url % symbol.lower(), symbol))

    # print data_list

    c = 1000
    while c < len(data_list):
        cursor.executemany(sql_insert, tuple(data_list[c - 1000: c]))
        c += 1000
    cursor.executemany(sql_insert, tuple(data_list[c-1000:]))
    db.commit()

# print get_insert_data('http://hq.sinajs.cn/list=sh000023', 'SH000023')
get_stock()
cursor.close()
db.close()
