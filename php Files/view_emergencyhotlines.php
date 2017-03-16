<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewEmergencyHotlines
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_emergencyhotline_exist()
		{
			$query = "select hotline_name, hotline_number from emergency_hotlines";
			$result = mysqli_query($this->connection, $query);
			$json = array();
			
			if(mysqli_num_rows($result))
			{
				while($row=mysqli_fetch_assoc($result))
				{
					$json[]=$row;
					
				}
			}
			else
			{
				$json['error'] = 'Please check your internet connection';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$viewOrders = new ViewEmergencyHotlines();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewOrders -> does_emergencyhotline_exist();
	}
?> 				