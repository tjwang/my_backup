delete from pAmountNew where date='20100607';
delete from pLast where date='20100607';
delete from pmK where date='20100607';
delete from pamountinfo where date='20100607';
delete from pamountinfo4A where date='20100607';
delete from pamountinfo4 where date='20100607';
delete from pamountinfo3 where date='20100607';
delete from pamountinfo2 where date='20100607';
delete from pmamountinfo_week where date='20100608';
delete from pmamountinfo where date='20100607';
delete from pfive_info where date='20100607';
delete from pfive_infoA where date='20100607';
delete from pfive_infoD where date='20100607';

cd %TSCODE%\�즨�����
call loadData.bat 20100607  

cd %TSCODE%\���̤j�R�W������q
call do.bat 20100607     

cd %TSCODE%\�p�⦨�����
call do.bat 20100607  

cd %TSCODE%\�p����ӶR����B
call do.bat 20100607

cd %TSCODE%\�p�����Ӹ�T
call do.bat 20100607  

cd %TSCODE%\�p��̨Τ��� 
call do.bat 20100607
call doAD.bat 20100607

cd %TSCODE%\�p��j�L��T
java CulMoney3 20100607 > 20100607.txt
java DoSql03 20100607                               
del 20100607.txt
