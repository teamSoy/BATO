<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class Register // for register
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_user_exist($firstname,$lastname,$gender,$birthday,$email_address,$contact_no,$profile_picture,$username,$password)
			{
				$query = "select * from users where email_address = '$email_address' OR username='$username'";
				$result = mysqli_query($this->connection, $query);
				$row_cnt = $result->num_rows;

				if ($row_cnt > 0) {

				   $json['error'] = 'Email or Username already in use.' ;

				}
				else
				{
					$query2 = "insert into users(account_id,first_name,last_name,gender,birthday,email_address,contact_no,profile_picture,username,password) VALUES (1,'$firstname','$lastname','$gender','$birthday','$email_address','$contact_no','$profile_picture','$username','$password')";
					$is_inserted = mysqli_query($this->connection, $query2);
					if($is_inserted == 1)
					{
						
						$json['success'] = ''.$firstname;
					}
					else
					{
						$json['error'] = ' An error occured. Please check your internet fake ' ;
						}
				}
					
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
	}
	$register = new Register();
	if(isset($_POST['first_name'],$_POST['last_name'],$_POST['gender'],$_POST['birthday'],$_POST['email_address'],$_POST['contact_no'],$_POST['profile_picture'],$_POST['username'],$_POST['password']))
	{
		$firstname = $_POST['first_name'];
		 $lastname = $_POST['last_name'];
		$gender = $_POST['gender'];
		$birthday = $_POST['birthday'];
		$email_address = $_POST['email_address'];
		$contact_no = $_POST['contact_no'];
		$profile_picture = $_POST['profile_picture'];
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		if(!empty($firstname) && !empty($lastname) && !empty($gender) && !empty($birthday) && !empty($email_address) && !empty($contact_no) && !empty($profile_picture) && !empty($username) && !empty($password))
		{
			$register -> does_user_exist($firstname,$lastname,$gender,$birthday,$email_address,$contact_no,$profile_picture,$username,$password);
		}
		else
		{
			echo json_encode("Complete all textfield first!");
		}
	}
?> 						