<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class PostReport
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_report_exist($latitude,$longitude,$descriptions,$report_picture1,$username,$situation_name)
		{
			$viewSituationID = "select situation_id from situation where situation_name like '$situation_name'";
			$result = mysqli_query($this->connection,$viewSituationID);
			$situationID = mysqli_fetch_row($result);
			
			$viewUserID = "select user_id from users where username like '$username'";
			$result2 = mysqli_query($this->connection,$viewUserID);
			$userID = mysqli_fetch_row($result2);
			
			$query = "insert into reports(user_id,situation_id,latitude,longitude,descriptions,report_picture1) values ('$userID[0]','$situationID[0]','$latitude','$longitude','$descriptions','$report_picture1')";
			$result3 = mysqli_query($this->connection,$query);
			if($result3)
			{
				
				$json['success'] = 'Report Posted Successfully!!';
			}
			else
			{
				$json['error'] = 'Please check your internet connection';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$postReport = new PostReport();
	if(isset($_POST['latitude'],$_POST['longitude'],$_POST['descriptions'],$_POST['report_picture1']),$_POST['username']),$_POST['situation_name'])
	{
		$latitude = $_POST['latitude'];
		$longitude = $_POST['longitude'];
		$descriptions = $_POST['descriptions'];
		$report_picture1 = $_POST['report_picture1'];
		$username = $_POST['username'];
		$situation_name= $_POST['situation_name'];
		$postReport -> does_report_exist($latitude,$longitude,$descriptions,$report_picture1,$username,$situation_name);
	}
?> 				