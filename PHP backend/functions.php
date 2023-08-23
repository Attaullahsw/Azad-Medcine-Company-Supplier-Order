<?php

function cleanVideoLink($input) {
    $a = trim($input);
    $a = strip_tags($a);
    $a = implode("", explode("\\", $a));
    $a = implode("", explode("?", $a));
    $a = implode("", explode('"', $a));
    $a = implode("", explode("'", $a));
    $a = stripslashes($a);
    $a = htmlspecialchars($a);
    return $a;
}

/**
 * @param type User Input Value
 * @return type Clean Value
 */
function cleanTextAreaInput($input) {
    $a = trim($input);
    $a = implode("", explode("\\", $a));
    $a = implode("", explode("?", $a));
    $a = implode("", explode('"', $a));
    $a = implode("", explode("'", $a));
    $a = stripslashes($a);
    return $a;
}

/**
 * @param type User Input Value
 * @return type Clean Value
 */
function cleanFields($input) {
    $a = trim($input);
    $a = strip_tags($a);
    $a = implode("", explode("/", $a));
    $a = implode("", explode("\\", $a));
    $a = implode("", explode("?", $a));
    $a = implode("", explode('"', $a));
    $a = implode("", explode("'", $a));
    $a = stripslashes($a);
    $a = htmlspecialchars($a);
    return $a;
}

/**
 * @param type User Input Value
 * @return type Clean Value
 */
function cleanInput($input) {
    $a = trim($input);
    $a = strip_tags($a);
    $a = implode("", explode("/", $a));
    $a = implode("", explode("\\", $a));
    $a = implode("", explode("?", $a));
    $a = implode("", explode('"', $a));
    $a = implode("", explode("'", $a));
    $a = implode("", explode("(", $a));
    $a = implode("", explode(")", $a));
    $a = implode("", explode("=", $a));
    $a = stripslashes($a);
    $a = htmlspecialchars($a);
    return $a;
}

function clearImportent($input) {
    $a = trim($input);
    $a = strip_tags($a);
    $a = htmlspecialchars($a);
    return $a;
}

/**
 * @param type Alert Type
 * @param type Alert Message
 */
function message($alert_type, $alert_msg) {
    $type = $alert_type;
    $msg = $alert_msg;
    switch ($type) {
        case 'danger':
            ?>
            <div class='alert alert-danger alert-dismissibl' style="border-radius: 0px; margin-bottom: 3px;" role='alert'>
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <strong>Alert!</strong> <?php echo $msg ?>
            </div>
            <?php
            break;
        case 'success':
            ?>
            <div class='alert alert-success alert-dismissibl' role='alert'>
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <strong>Alert!</strong> <?php echo $msg ?>
            </div>
            <?php
            break;
        case 'info':
            ?>
            <div class='alert alert-info alert-dismissibl' role='alert'>
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <strong>Alert!</strong> <?php echo $msg ?>
            </div>
            <?php
            break;
        case 'warning':
            ?>
            <div class='alert alert-warning alert-dismissibl' role='alert'>
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                <strong>Alert!</strong> <?php echo $msg ?>
            </div>
            <?php
            break;
        default:
            echo "<div class='alert alert-danger'><strong>Alert!</strong> Invalid Alert Class Name</div>";
            break;
    }
}

function footer() {
    ?>
    <div class="row" style="margin-top: 30px;">
        <div  class="col-sm-12 text-center" style="background-color: gray;color:#fff;">
            <h5><b>All Right Reserved  &copy; <?php echo date('Y'); ?></b></h5>
        </div>
    </div>
    <?php
}
?>