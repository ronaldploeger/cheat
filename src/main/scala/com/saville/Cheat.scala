package com.saville

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConversions._
import com.typesafe.config.Config
import com.typesafe.config.ConfigObject
import com.typesafe.config.ConfigValue
import java.util.Map.Entry
import com.typesafe.config.impl.ConfigString
import com.typesafe.config.impl.ConfigString
import com.typesafe.config.ConfigValueType
import scala.util.Try
import com.typesafe.config.ConfigException

object Cheat {
  val systemKeys = ConfigFactory.systemProperties().root().keySet()
  val envKeys = ConfigFactory.systemEnvironment().root().keySet()

  def main(args: Array[String]): Unit = {
    val path: Option[String] = getPath(args)
    val maxLevel: Option[Int] = getMaxLevel(args)
    val entries = loadConfigEntries(path);
    printConfigEntries(entries, maxLevel = maxLevel)
  }

  private def getPath(args: Array[String]): Option[String] = {
    if (args.size > 0 && Try(args(0).toInt).isFailure) {
      Some(args(0))
    } else {
      None
    }
  }

  private def getMaxLevel(args: Array[String]): Option[Int] = {
    if (args.size > 0 && Try(args(0).toInt).isSuccess) {
      Some(args(0).toInt)
    } else if (args.size > 1 && Try(args(1).toInt).isSuccess) {
      Some(args(1).toInt)
    } else {
      None
    }
  }

  private def loadConfigEntries(path: Option[String]): Map[String, Object] = {
    val cheats = ConfigFactory.load("cheats.conf");

    val config = if (path.isDefined) {
      try {
        cheats.getObject(path.get)      
      } catch {
        case e: ConfigException.WrongType => {
          println(cheats.getString(path.get))
          return Map()
        }
      }
      
    } else {
      cheats.root()
    }

    config.unwrapped().toMap
  }

  private def printConfigEntries(entries: Map[String, Object], maxLevel: Option[Int], level: Int = 0): Unit = {
    val space = " " * level * 2;
    val keys = entries.keys.toSeq.sortWith(_.toLowerCase() < _.toLowerCase())

    for (key <- keys) {
      if (!maxLevel.isDefined || maxLevel.get > level) {
        if (level != 0 || (!systemKeys.contains(key) && !envKeys.contains(key))) {
          val value = entries(key)
          value match {
            case map: java.util.Map[String, Object] => {
              println(f"${space}${key}:")
              printConfigEntries(entries = map.toMap, maxLevel = maxLevel, level = level + 1)
            }
            case _ => println(f"${space}${key}%-15s ${value}")
          }
        }
      }
    }
  }
}
