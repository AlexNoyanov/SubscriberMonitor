<?

// Script for connection to the MYSQL batabase
// with subscribers 

// Sat 19 October 2019

$host="localhost"; 		// Host name.
//$host = " Air-MacBook-of-Alex-Noyanov.local ";
$db_user="root"; 					// MySQL username.
$db_password="alexnoyanov"; 		// MySQL password.
$database="Subscribers"; 		    // Database name.
$link = mysql_connect($host,$db_user,$db_password);

if (!$link) {
   die('Could not connect: ' . mysql_error());
}
else
{
echo "Mysql Connected Successfully";
}

mysql_close($link);
?>