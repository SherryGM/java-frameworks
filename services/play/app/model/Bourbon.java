package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Bourbon extends Model {
  @Id
  public Integer id;

	public String name;

	public String description;

	public BigDecimal price;

	public String shortname;

  public static Finder<Integer,Bourbon> find = new Finder<Integer,Bourbon>(Integer.class, Bourbon.class);

}
