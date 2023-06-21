import pandas as pd
import pymysql

# Excel 데이터 pandas의 Dataframe으로 변환
excelData = pd.ExcelFile("./korea-location-data.xlsx")
dataFrame = excelData.parse('합본 DB')

# AWS RDS 연결
mysql_connection = pymysql.connect(
    host='database-chatbot.c5a7ow4d3vcq.ap-northeast-2.rds.amazonaws.com',
    database='garden',
    user='admin',
    password='everyonesgarden'
)
cursor = mysql_connection.cursor()

# Dataframe에 있는 데이터 변환해서 차례대로 AWS RDS에 INSERT
insert_sql = 'INSERT INTO location ' \
             '(level1, level2, level3, level4, latitude, longitude) ' \
             'values (%s, %s, %s, %s, %s, %s)'

for i, row in dataFrame.iterrows():
    rowData = [row[column]
               if pd.notnull(row[column]) else None
               for column in ['시도', '시군구', '읍면동', '하위', '위도', '경도']]

    cursor.execute(insert_sql, tuple(rowData))

    print(f"Processed {i}, {rowData}")

mysql_connection.commit()
cursor.close()
mysql_connection.close()
